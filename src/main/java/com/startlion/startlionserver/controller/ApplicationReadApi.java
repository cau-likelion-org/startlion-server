package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.dto.response.application.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

public interface ApplicationReadApi {

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
    ResponseEntity<ApplicationGetResponse> getApplicationById(
            @PathVariable Long applicationId,
            Principal principal);


    @Operation(summary = "지원서 페이지 2 정보 가져오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "지원서 페이지 2 정보 가져오기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 페이지 2 정보 없음")
            }
    )
    ResponseEntity<ApplicationPage2GetResponse> getApplicationPage2ById(
            @PathVariable Long applicationId,
            Principal principal);

    @Operation(summary = "지원서 페이지 3 정보 가져오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "지원서 페이지 3 정보 가져오기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 페이지 3 정보 없음")
            }
    )
    ResponseEntity<ApplicationPage3GetResponse> getApplicationPage3ById(
            @PathVariable Long applicationId,
            Principal principal);

    @Operation(summary = "지원서 페이지 4 정보 가져오기")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "지원서 페이지 4 정보 가져오기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음"),
                    @ApiResponse(responseCode = "404", description = "지원서 페이지 4 정보 없음")
            }
    )
    ResponseEntity<ApplicationPage4Response> getApplicationPage4ById(
            @PathVariable Long applicationId,
            Principal principal);
}
