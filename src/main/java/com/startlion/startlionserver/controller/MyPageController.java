package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.application.ApplicationListGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationListWithSubmittedResponse;
import com.startlion.startlionserver.service.MyPageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/myPage")
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService mypageService;

    @Operation(summary = "myPage 정보 조회")
    @GetMapping("{userId}")
    public ResponseEntity<ApplicationListWithSubmittedResponse> getApplicationList(@PathVariable Long userId) {
        ApplicationListWithSubmittedResponse response = mypageService.getApplicationList(userId);
        return ResponseEntity.ok(response);
    }
}
