package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.request.application.ApplicationPage1Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4Request;
import com.startlion.startlionserver.dto.response.application.ApplicationPage1Response;
import com.startlion.startlionserver.service.ApplicationCommandService;
import com.startlion.startlionserver.service.ApplicationQueryService;
import com.startlion.startlionserver.util.UserUtil;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/application")
@RequiredArgsConstructor
public class ApplicationController implements ApplicationApi {

    private final ApplicationCommandService applicationCommandService;

    @Override
    @PostMapping
    public ResponseEntity<ApplicationPage1Response> createApplication(
            @RequestBody ApplicationPage1Request request,
            Principal principal) {
        val response = applicationCommandService.createApplication(request, UserUtil.getUserId(principal));
        return ResponseEntity.ok(response);
    }

    @Override
    @PatchMapping("{applicationId}/page1")
    public ResponseEntity<Void> updateApplicationPage1(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage1Request request,
            Principal principal) {
        applicationCommandService.updateApplicationPage1(applicationId, request, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();

    }


    @Override
    @PatchMapping("/{applicationId}/page2")
    public ResponseEntity<Void> updateApplicationPage2(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage2Request request,
            Principal principal) {
        applicationCommandService.updateApplicationPage2(applicationId, request, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{applicationId}/page3")
    public ResponseEntity<Void> updateApplicationPage3(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage3Request request, Principal principal) {
        applicationCommandService.updateApplicationPage3(applicationId, request, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{applicationId}/page4")
    public ResponseEntity<Void> updateApplicationPage4(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage4Request request,
            Principal principal) {
        applicationCommandService.updateApplicationPage4(applicationId, request, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{applicationId}/submit")
    public ResponseEntity<Void> submitApplication(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            Principal principal) {
        applicationCommandService.submitApplication(applicationId, UserUtil.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

}
