package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.config.auth.AuthValueConfig;
import com.startlion.startlionserver.dto.response.auth.OAuthResponse;
import com.startlion.startlionserver.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "[Auth] 인증 관련 API")
public class AuthApiController {

    private final AuthService authService;
    private final AuthValueConfig valueConfig;
    @Operation(summary = "소셜 로그인")
    @GetMapping("/login")
    public ResponseEntity<Void> getGoogleAuthUrl(HttpServletResponse response) throws Exception {

        val reqUrl = valueConfig.getGoogleLoginUrl()
                + "/o/oauth2/v2/auth?client_id="
                + valueConfig.getGoogleClientId()
                + "&redirect_uri="
                + valueConfig.getGoogleRedirectUrl()
                + "&response_type=code&scope=email%20profile%20openid&access_type=offline";

        response.setHeader("Location", reqUrl);
        return ResponseEntity
                .status(HttpStatus.MOVED_PERMANENTLY)
                .build();
    }



@Operation(summary = "소셜 로그인 성공")
@GetMapping("/login/oauth2/code/google")
public ResponseEntity<Void> oauthGoogleCheck(@RequestParam(value = "code") String authCode) throws Exception {
    OAuthResponse response = authService.authenticateUser(authCode);

    String redirectUrl = "http://localhost:3000/auth?" + response.toString();

    return ResponseEntity.status(HttpStatus.FOUND)
            .header("Location", redirectUrl)
            .build();
}


    @Operation(summary = "멤버")
    @GetMapping("/member")
    public Authentication getCurrentUser(Authentication authentication) {
        return authentication;
    }
}