package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.request.application.*;
import com.startlion.startlionserver.dto.response.application.ApplicationPage1GetResponse;
import com.startlion.startlionserver.service.ApplicationService;
import com.startlion.startlionserver.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping(value = "/application")
@RequiredArgsConstructor
@Tag(name = "[Application] 지원서 관련 API")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Value("${global.generation}")
    Long generation;

    @Operation(summary = "지원서 정보 가져오기 (저장된 지원서가 없을 시(applicationId = 0), 지원서 1페이지 질문 가져오기)")
    @GetMapping("/{applicationId}")
    public Object getApplication(
            @PathVariable(required = false) Long applicationId,
            @RequestParam(required = false) Integer page,
            Principal principal) {
        if (applicationId == null || applicationId == 0) {
            return applicationService.getApplicationPersonalInformation();
        } else {
            return applicationService.getById(applicationId, page, UserUtil.getUserId(principal));
        }
    }

    // 지원서 저장하기의 경우 받아야 하는 Body 정보가 다르기 때문에, 4개의 API로 나누었습니다.
    // 지원서 저장하기 1페이지

    @Operation(summary = "지원서 저장하기 1페이지 -> GET application/으로 접근했을 때 사용")
    @PostMapping
    public ResponseEntity<String> postApplicationPage1(@RequestBody ApplicationPage1PutRequest request, Principal principal){
        Long applicationId = applicationService.createApplicationPage1(request, generation, UserUtil.getUserId(principal));
        URI uri = URI.create("/application/" + applicationId);
        return ResponseEntity.created(uri).body("Application ID: " + applicationId);
    }

    @Operation(summary = "지원서 저장하기 1페이지 -> GET application/{applicationId}으로 접근했을 때 사용")
    @PutMapping("/{applicationId}/page1")
    public ResponseEntity<String> updateApplicationPage1(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage1PutRequest request, Principal principal){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage1(applicationId, request, generation, UserUtil.getUserId(principal)));
        return ResponseEntity.created(uri).body("지원서 1페이지 저장 완료");
    }

    // 지원서 저장하기 2페이지
    @Operation(summary = "지원서 저장하기 2페이지")
    @PutMapping("/{applicationId}/page2")
    public ResponseEntity<String> updateApplicationPage2(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage2PutRequest request, Principal principal){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage2(applicationId, request, UserUtil.getUserId(principal)));
        return ResponseEntity.created(uri).body("지원서 2페이지 저장 완료");
    }

    // 지원서 저장하기 3페이지
    @Operation(summary = "지원서 저장하기 3페이지")
    @PutMapping("/{applicationId}/page3")
    public ResponseEntity<String> updateApplicationPage3(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage3PutRequest request, Principal principal){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage3(applicationId, request, UserUtil.getUserId(principal)));
        return ResponseEntity.created(uri).body("지원서 3페이지 저장 완료");
    }

    // 지원서 저장하기 4페이지 -> 제출
    @Operation(summary = "지원서 저장하기 4페이지 -> 제출")
    @PutMapping("/{applicationId}/page4")
    public ResponseEntity<String> updateApplicationPage4(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage4PutRequest request, Principal principal){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage4(applicationId, request, UserUtil.getUserId(principal)));
        return ResponseEntity.created(uri).body("지원서 제출 완료");
    }

}
