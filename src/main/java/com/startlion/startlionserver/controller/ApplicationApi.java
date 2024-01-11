package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.request.application.ApplicationPage1Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4Request;
import com.startlion.startlionserver.dto.response.application.ApplicationGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationsGetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Tag(name = "[Application] 지원서 관련 API")
public interface ApplicationApi {

    @Operation(summary = "지원서 목록 가져오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "지원서 목록 가져오기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 목록 없음")
            }
    )
    ResponseEntity<ApplicationsGetResponse> getApplications(Principal principal);

    @Operation(summary = "지원서 정보 가져오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "지원서 정보 가져오기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음")
            }
    )
    ResponseEntity<ApplicationGetResponse> getApplication (@PathVariable(required = false) Long applicationId, Principal principal);

    @Operation(summary = "지원서 저장하기 1페이지")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "지원서 저장하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음")
            }
    )
    ResponseEntity<Void> createApplication(@RequestBody ApplicationPage1Request request, Principal principal);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "지원서 저장하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음")
            }
    )
    @Operation(summary = "지원서 저장하기 2페이지")
    ResponseEntity<Void> updateApplicationPage2(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage2Request request, Principal principal);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "지원서 3page 저장하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음")
            }
    )
    @Operation(summary = "지원서 저장하기 3페이지")
    ResponseEntity<Void> updateApplicationPage3(@PathVariable @Parameter(description = "지원서 ID") Long applicationId, @RequestBody ApplicationPage3Request request, Principal principal);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "지원서 저장하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음")
            }
    )

    @Operation(summary = "지원서 저장하기 4페이지 -> 제출")
    ResponseEntity<Void> updateApplicationPage4(@PathVariable @Parameter(description = "지원서 ID") Long applicationId,
                                                  @RequestBody ApplicationPage4Request request,
                                                  Principal principal);

    @Operation(summary = "지원서 제출하기")
    ResponseEntity<Void> submitApplication(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            Principal principal);
}
