package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.request.application.ApplicationPage1Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4Request;
import com.startlion.startlionserver.dto.response.application.ApplicationGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationsGetResponse;
import com.startlion.startlionserver.service.ApplicationCommandService;
import com.startlion.startlionserver.service.ApplicationQueryService;
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

    private final ApplicationQueryService applicationQueryService;
    private final ApplicationCommandService applicationCommandService;


    @Override
    @GetMapping
    public ResponseEntity<ApplicationsGetResponse> getApplications(Principal principal) {
        val response = applicationQueryService.getApplications(UserUtil.getUserId(principal));
        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationGetResponse> getApplication(
            @PathVariable Long applicationId,
            Principal principal) {
        val response = applicationQueryService.getApplication(applicationId, UserUtil.getUserId(principal));
        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> createApplication(
            @RequestBody ApplicationPage1Request request,
            Principal principal){
        val applicationId = applicationCommandService.createApplication(request, UserUtil.getUserId(principal));
        return ResponseEntity.created(URI.create(applicationId.toString())).build();

    }

    @Override
    @PatchMapping("/{applicationId}/page2")
    public ResponseEntity<Void> updateApplicationPage2(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage2Request request,
            Principal principal){
        applicationCommandService.updateApplicationPage2(applicationId, request, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{applicationId}/page3")
    public ResponseEntity<Void> updateApplicationPage3(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage3Request request, Principal principal){
        applicationCommandService.updateApplicationPage3(applicationId, request, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{applicationId}/page4")
    public ResponseEntity<Void> updateApplicationPage4(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage4Request request,
            Principal principal){
        applicationCommandService.updateApplicationPage4(applicationId, request, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{applicationId}/submit")
    public ResponseEntity<Void> submitApplication(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            Principal principal){
        applicationCommandService.submitApplication(applicationId, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

}
