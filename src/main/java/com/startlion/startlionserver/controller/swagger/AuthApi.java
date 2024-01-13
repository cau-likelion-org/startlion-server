package com.startlion.startlionserver.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "[Auth] 인증 관련 API")
public interface AuthApi {

    @Operation(summary = "소셜 로그인")
    ResponseEntity<Void> getGoogleAuthUrl(HttpServletResponse response) throws Exception;

    @Operation(summary = "소셜 로그인 성공")
    ResponseEntity<Void> oauthGoogleCheck(@RequestParam(value = "code") String authCode) throws Exception;
}
