package com.startlion.startlionserver.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@Tag(name = "[Health] 서버 상태 체크 API")
public class HealthCheckController {

    @ApiResponse(responseCode = "200", description = "항상 OK를 반환합니다.")
    @GetMapping
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.ok("OK");
    }
}
