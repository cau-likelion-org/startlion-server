package com.startlion.startlionserver.auth;

import com.startlion.startlionserver.domain.entity.User;
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

@Slf4j
@RestController
public class ApiRestController {

    @Autowired
    private AuthService authService;

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

    @GetMapping("/login")
    public ResponseEntity<?> getGoogleAuthUrl() throws Exception {

        String reqUrl = googleLoginUrl + "/o/oauth2/v2/auth?client_id=" + googleClientId + "&redirect_uri=" + googleRedirectUrl
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

        log.info(reqUrl);
        log.info("myLog-LoginUrl : {}",googleLoginUrl);
        log.info("myLog-ClientId : {}",googleClientId);
        log.info("myLog-RedirectUrl : {}",googleRedirectUrl);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(reqUrl));

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);


    }

    @GetMapping("/login/oauth2/code/google")
    public User oauth_google_check(@RequestParam(value = "code") String authCode) throws Exception{

        User returnvalue=authService.authenticateUser(authCode);
        return returnvalue;
    }

    @GetMapping("/member")
    public Authentication getCurrentUser(Authentication authentication) {
        return authentication;
    }
}