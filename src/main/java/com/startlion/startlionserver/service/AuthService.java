package com.startlion.startlionserver.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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

    @Value("${aws-bucket}")
    private String bucket;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AmazonS3 amazonS3;

    Map<String, Object> result = new HashMap<>();

    @Autowired
    private com.startlion.startlionserver.auth.JwtTokenProvider jwtTokenProvider;

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
            String s3Url = uploadImageToS3(imageUrl);
            User newUser = new User();
            newUser.join(email,username,socialId,s3Url);
            saveUserTokens(newUser);
            userRepository.save(newUser);
            return result;
        }
        else {
            User findUser = userRepository.findByEmail(email);
            // 이미지가 변경되었을 경우에만 S3에 업로드
            if(!imageUrl.equals(findUser.getPreviousImageUrl())) {
                String s3Url = uploadImageToS3(imageUrl);
                findUser.updateImageUrl(s3Url);
            }

            saveUserTokens(findUser);
            return result;
        }

    }

    // 이미지를 S3에 업로드하고 S3 URL을 반환
    private String uploadImageToS3(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        String fileName = UUID.randomUUID().toString();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/jpeg");
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, in, metadata));

        String s3Url = amazonS3.getUrl(bucket, fileName).toString();
        return s3Url;
    }

    @Value("${jwt.accessTokenExpiration}")
    private Long accessTokenExpiration;

    @Value("${jwt.refreshTokenExpiration}")
    private Long refreshTokenExpiration;

    private void saveUserTokens(User user) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
        String accessToken = jwtTokenProvider.generateToken(authentication, accessTokenExpiration);
        String refreshToken = jwtTokenProvider.generateToken(authentication, refreshTokenExpiration);
        user.saveToken(refreshToken);
        userRepository.save(user);
        result.put("user", user);
        result.put("accessToken", accessToken);
    }
}
