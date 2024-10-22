package com.startlion.startlionserver.controller.swagger;

import com.startlion.startlionserver.dto.request.application.*;
import com.startlion.startlionserver.dto.response.ErrorResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Tag(name = "[Application] 지원서 관련 API")
public interface ApplicationApi {

    @Operation(summary = "지원서 저장 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "지원서 저장 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음", content = @Content)
            }
    )
    ResponseEntity<ApplicationCreateResponse> createApplication(
            @RequestBody ApplicationCreateRequest request,
            Principal principal);

    @Operation(summary = "지원서 1페이지 정보를 저장합니다.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "지원서 저장하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음", content = @Content),
                    @ApiResponse(responseCode = "500", description = "알 수 없는 서버 에러", content = @Content(contentSchema = @Schema(implementation = ErrorResponse.class)
                    ))
            }
    )
    ResponseEntity<Void> updateApplicationPage1(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage1Request request,
            Principal principal);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "지원서 저장하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음", content = @Content)
            }
    )
    @Operation(summary = "지원서 업데이트 2페이지")
    ResponseEntity<Void> updateApplicationPage2(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage2Request request, Principal principal);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "지원서 3page 저장하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음", content = @Content)
            }
    )
    @Operation(summary = "지원서 업데이트 3페이지")
    ResponseEntity<Void> updateApplicationPage3(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            @RequestBody ApplicationPage3Request request, Principal principal);

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "지원서 저장하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음", content = @Content)
            }
    )

    @Operation(summary = "지원서 업데이트 4페이지")
    ResponseEntity<Void> updateApplicationPage4(@PathVariable @Parameter(description = "지원서 ID") Long applicationId,
                                                  @RequestBody ApplicationPage4Request request,
                                                  Principal principal);


    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "지원서 제출하기 성공"),
                    @ApiResponse(responseCode = "403", description = "권한 없음", content = @Content),
                    @ApiResponse(responseCode = "404", description = "지원서 정보 없음", content = @Content)
            }
    )
    @Operation(summary = "지원서 제출하기")
    ResponseEntity<Void> submitApplication(
            @PathVariable @Parameter(description = "지원서 ID") Long applicationId,
            Principal principal);
}
