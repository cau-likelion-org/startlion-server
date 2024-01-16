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


@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthValueConfig valueConfig;
    private final UserJpaRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final Long ACCESS_TOKEN_EXPIRATION = 1000L * 60 * 60 * 2 * 12; // 24시간
    private static final Long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 14; // 2주

    @Transactional
    public OAuthResponse authenticateUser(String authCode) throws Exception {

        val googleOAuthRequest = GoogleOAuthRequest
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

        val newUser = User.create(
                email,
                jsonNode.get("name").asText(),
                "google",
                jsonNode.get("picture").asText());

        User user = userRepository.findByEmail(email).orElseGet(() -> userRepository.save(newUser));
        val authentication = new UsernamePasswordAuthenticationToken(user.getUserId(), null, null);
        val tokenVO = generateToken(authentication);
        user.updateRefreshToken(tokenVO.refreshToken());
        return OAuthResponse.of(tokenVO.accessToken(), tokenVO.refreshToken());
    }

private TokenVO generateToken(Authentication authentication){
        val accessToken = jwtTokenProvider.generateToken(authentication,ACCESS_TOKEN_EXPIRATION);
        val refreshToken = jwtTokenProvider.generateToken(authentication,REFRESH_TOKEN_EXPIRATION);
        return TokenVO.of(accessToken, refreshToken);
        }
}
