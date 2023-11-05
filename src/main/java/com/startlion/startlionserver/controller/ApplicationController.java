package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.application.ApplicationGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPersonalInformationGetResponse;
import com.startlion.startlionserver.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<ApplicationPersonalInformationGetResponse> getApplicationPersonalInformation(){
        return ResponseEntity.ok(applicationService.getApplicationPersonalInformation());
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationGetResponse> getApplication(@PathVariable Long applicationId){
        return ResponseEntity.ok(applicationService.getById(applicationId));
    }

    @PutMapping
    public ResponseEntity<ApplicationGetResponse> updateApplication(@PathVariable Long applicationId){
        return ResponseEntity.ok(applicationService.updateApplication(applicationId));
    }



}
