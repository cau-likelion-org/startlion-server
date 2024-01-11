package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.request.application.ApplicationPage1PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3PutRequest;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4PutRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Tag(name = "[Application] 지원서 관련 API")
public interface ApplicationApi {

    @Operation(summary = "지원서 정보 가져오기 (저장된 지원서가 없을 시(applicationId = 0), 지원서 1페이지 질문 가져오기)")
    Object getApplication(
            @PathVariable(required = false) Long applicationId,
            @RequestParam(required = false) Integer page,
            Principal principal);

    @Operation(summary = "지원서 저장하기 1페이지 -> GET application/으로 접근했을 때 사용")
    ResponseEntity<String> postApplicationPage1(@RequestBody ApplicationPage1PutRequest request, Principal principal);

    @Operation(summary = "지원서 저장하기 1페이지 -> GET application/{applicationId}으로 접근했을 때 사용")
    ResponseEntity<String> updateApplicationPage1(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage1PutRequest request, Principal principal);

    @Operation(summary = "지원서 저장하기 2페이지")
    ResponseEntity<String> updateApplicationPage2(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage2PutRequest request, Principal principal);

    @Operation(summary = "지원서 저장하기 3페이지")
    ResponseEntity<String> updateApplicationPage3(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage3PutRequest request, Principal principal);

    @Operation(summary = "지원서 저장하기 4페이지 -> 제출")
    ResponseEntity<String> updateApplicationPage4(@PathVariable @Parameter(description = "지원서 ID") Long applicationId,
                                                  @RequestBody ApplicationPage4PutRequest request,
                                                  @RequestParam(required = false) Boolean isSubmit,
                                                  Principal principal);

}
