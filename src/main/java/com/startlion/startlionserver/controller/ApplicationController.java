package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.request.application.*;
import com.startlion.startlionserver.dto.response.application.ApplicationPage1GetResponse;
import com.startlion.startlionserver.service.ApplicationService;
import com.startlion.startlionserver.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    // 저장된 지원서 없을 시, 지원서 1페이지 정보(질문) 가져오기
    @Operation(summary = "저장된 지원서 없을 시, 지원서 1페이지 정보(질문) 가져오기")
    @GetMapping
    public ResponseEntity<ApplicationPage1GetResponse> getApplicationPersonalInformation(){
        return ResponseEntity.ok(applicationService.getApplicationPersonalInformation());
    }

    // 저장된 지원서 있을 시, 지원서 정보 가져오기
    @Operation(summary = "저장된 지원서 있을 시, 지원서 정보 가져오기")
    @GetMapping("/{applicationId}")
    public ResponseEntity<?> getApplication(
            @PathVariable Long applicationId,
            @RequestParam int page,
            Principal principal)  {
        return ResponseEntity.ok(applicationService.getById(applicationId, page, UserUtil.getUserId(principal)));
    }

    // 지원서 저장하기의 경우 받아야 하는 Body 정보가 다르기 때문에, 4개의 API로 나누었습니다.
    // 지원서 저장하기 1페이지
    @Operation(summary = "지원서 저장하기 1페이지")
    @PutMapping("/{applicationId}/page1")
    public ResponseEntity<String> updateApplicationPage1(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage1PutRequest request, @RequestParam Long generationId, Principal principal){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage1(applicationId, request, generationId, UserUtil.getUserId(principal)));
        return ResponseEntity.created(uri).body("지원서 1페이지 저장 완료");
    }

    // 지원서 저장하기 2페이지
    @Operation(summary = "지원서 저장하기 2페이지")
    @PutMapping("/{applicationId}/page2")
    public ResponseEntity<String> updateApplicationPage2(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage2PutRequest request){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage2(applicationId, request));
        return ResponseEntity.created(uri).body("지원서 2페이지 저장 완료");
    }

    // 지원서 저장하기 3페이지
    @Operation(summary = "지원서 저장하기 3페이지")
    @PutMapping("/{applicationId}/page3")
    public ResponseEntity<String> updateApplicationPage3(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage3PutRequest request){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage3(applicationId, request));
        return ResponseEntity.created(uri).body("지원서 3페이지 저장 완료");
    }

    // 지원서 저장하기 4페이지 -> 제출
    @Operation(summary = "지원서 저장하기 4페이지 -> 제출")
    @PutMapping("/{applicationId}/page4")
    public ResponseEntity<String> updateApplicationPage4(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage4PutRequest request){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage4(applicationId, request));
        return ResponseEntity.created(uri).body("지원서 제출 완료");
    }

}
