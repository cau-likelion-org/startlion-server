package com.startlion.startlionserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.startlion.startlionserver.dto.response.GoogleLoginResponse;
import com.startlion.startlionserver.dto.request.GoogleOAuthRequest;
import com.startlion.startlionserver.repository.UserRepository;
import com.startlion.startlionserver.domain.entity.User;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


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

    Map<String, Object> result = new HashMap<>();

    @Autowired
    private com.startlion.startlionserver.auth.jwtTokenProvider jwtTokenProvider;

    @Transactional
    public Map<String, Object> authenticateUser(String authCode) throws Exception{

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
            newUser.join(email,username,socialId,imageUrl);
            saveUserTokens(newUser);
            userRepository.save(newUser);
            return result;
        }
        else {
            User findUser = userRepository.findByEmail(email);
            saveUserTokens(findUser);
            return result;
        }

    }
    private void saveUserTokens(User user) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        String accessToken = jwtTokenProvider.generateToken(authentication, "access");
        String refreshToken = jwtTokenProvider.generateToken(authentication, "refresh");
        user.saveToken(refreshToken);
        userRepository.save(user);
        result.put("user", user);
        result.put("accessToken", accessToken);
    }
}
