package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.request.application.*;
import com.startlion.startlionserver.dto.response.application.ApplicationPage1GetResponse;
import com.startlion.startlionserver.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping(value = "/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    // 저장된 지원서 없을 시, 지원서 1페이지 정보 가져오기
    @GetMapping
    public ResponseEntity<ApplicationPage1GetResponse> getApplicationPersonalInformation(){
        return ResponseEntity.ok(applicationService.getApplicationPersonalInformation());
    }

    // 저장된 지원서 있을 시, 지원서 정보 가져오기
    @GetMapping("/{applicationId}")
    public ResponseEntity<?> getApplication(@PathVariable Long applicationId, @RequestParam int page)  {
        return ResponseEntity.ok(applicationService.getById(applicationId, page));
    }

    // 지원서 저장하기 1페이지
    @PutMapping("/{applicationId}/page1")
    public ResponseEntity<Void> updateApplicationPage1(@PathVariable Long applicationId, @RequestBody ApplicationPage1PutRequest request, @RequestParam Long generationId){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage1(applicationId, request, generationId));
        return ResponseEntity.created(uri).build();
    }

    // 지원서 저장하기 2페이지
    @PutMapping("/{applicationId}/page2")
    public ResponseEntity<Void> updateApplicationPage2(@PathVariable Long applicationId, @RequestBody ApplicationPage2PutRequest request){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage2(applicationId, request));
        return ResponseEntity.created(uri).build();
    }

    // 지원서 저장하기 3페이지
    @PutMapping("/{applicationId}/page3")
    public ResponseEntity<Void> updateApplicationPage3(@PathVariable Long applicationId, @RequestBody ApplicationPage3PutRequest request){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage3(applicationId, request));
        return ResponseEntity.created(uri).build();
    }

    // 지원서 저장하기 4페이지 -> 제출
    @PutMapping("/{applicationId}/page4")
    public ResponseEntity<Void> updateApplicationPage4(@PathVariable Long applicationId, @RequestBody ApplicationPage4PutRequest request){
        URI uri = URI.create("/application/" + applicationService.updateApplicationPage4(applicationId, request));
        return ResponseEntity.created(uri).build();
    }

}
