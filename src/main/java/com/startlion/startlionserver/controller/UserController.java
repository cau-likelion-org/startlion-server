package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.application.ApplicationListWithSubmittedResponse;
import com.startlion.startlionserver.service.UserService;
import com.startlion.startlionserver.util.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApplicationListWithSubmittedResponse> getApplicationList(
            Principal principal) {
        val response = userService.getApplicationList(UserUtil.getUserId(principal));
        return ResponseEntity.ok(response);
    }
}
