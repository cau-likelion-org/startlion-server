package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.service.AuthService;
import com.startlion.startlionserver.domain.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;

@Slf4j
@RestController
public class AuthApiController {

    private final AuthService authService;
    @Autowired
    public AuthApiController(AuthService authService) {
        this.authService = authService;
    }

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

    private String reqUrl;
    @Operation(summary = "소셜 로그인")
    @GetMapping("/login")
    public ResponseEntity<?> getGoogleAuthUrl() throws Exception {

        reqUrl = googleLoginUrl + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri=" + googleRedirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @Operation(summary = "소셜 로그인 성공")
    @GetMapping("/login/oauth2/code/google")
    public Map<String, Object> oauthGoogleCheck(@RequestParam(value = "code") String authCode) throws Exception{

        Map<String, Object> returnvalue=authService.authenticateUser(authCode);
        return returnvalue;
    }

    @Operation(summary = "멤버")
    @GetMapping("/member")
    public Authentication getCurrentUser(Authentication authentication) {
        return authentication;
    }
}