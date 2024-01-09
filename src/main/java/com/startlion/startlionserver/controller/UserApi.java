package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.application.ApplicationListWithSubmittedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

@Tag(name = "[User] 유저 관련 API")
public interface UserApi {

    @Operation(summary = "내 정보 조회")
    ResponseEntity<ApplicationListWithSubmittedResponse> getApplicationList(Principal principal);
}
