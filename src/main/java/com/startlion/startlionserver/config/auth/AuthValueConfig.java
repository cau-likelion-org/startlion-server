package com.startlion.startlionserver.config.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AuthValueConfig {

    @Value("${google.auth-url}")
    private String googleAuthUrl;

    @Value("${google.login-url}")
    private String googleLoginUrl;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUrl;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;
}
