package com.startlion.startlionserver.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.startlion.startlionserver.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;


@Service
public class AuthService {
    @Value("${google.auth.url}")
    private String googleAuthUrl;

    @Value("${google.login.url}")
    private String googleLoginUrl;

    @Value("${google.client.id}")
    private String googleClientId;

    @Value("${google.redirect.url}")
    private String googleRedirectUrl;

    @Value("${google.secret}")
    private String googleClientSecret;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private jwtTokenProvider jwtTokenProvider;
    public User authenticateUser(String authCode) throws Exception{

        GoogleOAuthRequest googleOAuthRequest = GoogleOAuthRequest
                .builder()
                .clientId(googleClientId)
                .clientSecret(googleClientSecret)
                .code(authCode)
                .redirectUri(googleRedirectUrl)
                .grantType("authorization_code")
                .build();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GoogleLoginResponse> apiResponse = restTemplate.postForEntity(googleAuthUrl + "/token", googleOAuthRequest, GoogleLoginResponse.class);

        GoogleLoginResponse googleLoginResponse = apiResponse.getBody();


        String googleToken = googleLoginResponse.getId_token();

        String requestUrl = UriComponentsBuilder.fromHttpUrl(googleAuthUrl + "/tokeninfo").queryParam("id_token", googleToken).toUriString();

        String resultJson = restTemplate.getForObject(requestUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(resultJson);
        String email = jsonNode.get("email").asText();
        String socialId = "google";
        String username = jsonNode.get("name").asText();
        String imageUrl = jsonNode.get("picture").asText();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            User newUser = new User();


            Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, Collections.emptyList());
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            newUser.join(email,username,socialId,imageUrl);
            newUser.saveToken(accessToken,refreshToken);
            userRepository.save(newUser);

            return newUser;
        }
        else {
            User findUser = userRepository.findByEmail(email);
            Authentication authentication = new UsernamePasswordAuthenticationToken(findUser, null, Collections.emptyList());
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);
            findUser.saveToken(accessToken,refreshToken);
            userRepository.save(findUser);
            return findUser;
        }

    }
}
