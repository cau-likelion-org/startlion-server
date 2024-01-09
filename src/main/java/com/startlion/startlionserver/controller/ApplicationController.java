package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.request.application.ApplicationPage1PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4PutRequest;
import com.startlion.startlionserver.service.ApplicationService;
import com.startlion.startlionserver.util.UserUtil;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping(value = "/application")
@RequiredArgsConstructor
public class ApplicationController implements ApplicationApi {

    private final ApplicationService applicationService;
    private static final Long CURRENT_GENERATION = 12L;

    @Override
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
    @Override
    @PostMapping
    public ResponseEntity<String> postApplicationPage1(@RequestBody ApplicationPage1PutRequest request, Principal principal){
        Long applicationId = applicationService.createApplicationPage1(request, CURRENT_GENERATION, UserUtil.getUserId(principal));
        URI uri = URI.create("/application/" + applicationId);
        return ResponseEntity.created(uri).body("Application ID: " + applicationId);
    }

    @Override
    @PutMapping("/{applicationId}/page1")
    public ResponseEntity<String> updateApplicationPage1(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage1PutRequest request, Principal principal){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage1(applicationId, request, CURRENT_GENERATION, UserUtil.getUserId(principal)));
        return ResponseEntity.created(uri).body("지원서 1페이지 저장 완료");
    }

    // 지원서 저장하기 2페이지
    @Override
    @PutMapping("/{applicationId}/page2")
    public ResponseEntity<String> updateApplicationPage2(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage2PutRequest request, Principal principal){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage2(applicationId, request, UserUtil.getUserId(principal)));
        return ResponseEntity.created(uri).body("지원서 2페이지 저장 완료");
    }

    // 지원서 저장하기 3페이지
    @Override
    @PutMapping("/{applicationId}/page3")
    public ResponseEntity<String> updateApplicationPage3(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage3PutRequest request, Principal principal){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage3(applicationId, request, UserUtil.getUserId(principal)));
        return ResponseEntity.created(uri).body("지원서 3페이지 저장 완료");
    }

    // 지원서 저장하기 4페이지 -> 제출
    @Override
    @PutMapping("/{applicationId}/page4")
    public ResponseEntity<String> updateApplicationPage4(@PathVariable @Parameter(description = "지원서 ID") Long applicationId,
                                                         @RequestBody ApplicationPage4PutRequest request,
                                                         Principal principal){
        val userId = UserUtil.getUserId(principal);
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage4(applicationId, request, userId));
        return ResponseEntity.created(uri).body("지원서 제출 완료");
    }

}
