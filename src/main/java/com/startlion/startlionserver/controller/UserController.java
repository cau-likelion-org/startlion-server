package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.application.ApplicationListWithSubmittedResponse;
import com.startlion.startlionserver.service.UserService;
import com.startlion.startlionserver.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.aspectj.weaver.MemberUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "내 정보 조회")
    @GetMapping("/me")
    public ResponseEntity<ApplicationListWithSubmittedResponse> getApplicationList(
            Principal principal) {
        val response = userService.getApplicationList(UserUtil.getUserId(principal));
        return ResponseEntity.ok(response);
    }
}
