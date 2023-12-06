package com.startlion.startlionserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.startlion.startlionserver.config.auth.AuthValueConfig;
import com.startlion.startlionserver.config.jwt.JwtTokenProvider;
import com.startlion.startlionserver.domain.entity.User;
import com.startlion.startlionserver.dto.request.GoogleOAuthRequest;
import com.startlion.startlionserver.dto.response.GoogleLoginResponse;
import com.startlion.startlionserver.dto.response.auth.OAuthResponse;
import com.startlion.startlionserver.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthValueConfig valueConfig;
    private final UserJpaRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final Long ACCESS_TOKEN_EXPIRATION = 1000L * 60 * 60 * 2; // 2시간
    private static final Long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 14; // 2주

    Map<String, Object> result = new HashMap<>();

    @Transactional
    public OAuthResponse authenticateUser(String authCode) throws Exception {

        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
                .builder()
                .clientId(valueConfig.getGoogleClientId())
                .clientSecret(valueConfig.getGoogleClientSecret())
                .code(authCode)
                .redirectUri(valueConfig.getGoogleRedirectUrl())
                .grantType(AUTHORIZATION_CODE)
                .build();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GoogleLoginResponse> apiResponse = restTemplate.postForEntity(valueConfig.getGoogleAuthUrl() + "/token", googleOAuthRequest, GoogleLoginResponse.class);

        GoogleLoginResponse googleLoginResponse = apiResponse.getBody();

        val googleToken = googleLoginResponse.getId_token();
        val requestUrl = UriComponentsBuilder.fromHttpUrl(valueConfig.getGoogleAuthUrl() + "/tokeninfo")
                .queryParam("id_token", googleToken)
                .toUriString();
        val resultJson = restTemplate.getForObject(requestUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(resultJson);
        val email = jsonNode.get("email").asText();
        val socialId = "google";
        val username = jsonNode.get("name").asText();
        val imageUrl = jsonNode.get("picture").asText();

        val newUser = User.builder()
                .email(email)
                .username(username)
                .socialId(socialId)
                .imageUrl(imageUrl)
                .build();

        User user = userRepository.findByEmail(email)
                .orElse(userRepository.save(newUser));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, null);
        val tokenVO = generateToken(authentication);

        user.updateRefreshToken(tokenVO.getRefreshToken());
        return OAuthResponse.of(tokenVO.getAccessToken(), tokenVO.getRefreshToken());
    }

private TokenVO generateToken(Authentication authentication){
        String accessToken=jwtTokenProvider.generateToken(authentication,ACCESS_TOKEN_EXPIRATION);
        String refreshToken=jwtTokenProvider.generateToken(authentication,REFRESH_TOKEN_EXPIRATION);
        return new TokenVO(accessToken,refreshToken);
        }
}
