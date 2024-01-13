package com.startlion.startlionserver.controller.swagger;


import com.startlion.startlionserver.dto.response.interview.InterviewDetailResponse;
import com.startlion.startlionserver.dto.response.interview.InterviewResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(name = "[Interview] 인터뷰 관련 API")
public interface InterviewApi {

    @Operation(summary = "interview 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "interview 정보 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "interview 정보 없음", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content)
            }
    )
    ResponseEntity<List<InterviewResponse>> getInterviews(@RequestParam(required = false) String part);

    @Operation(summary = "interviewId로 interview 정보 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "interview 정보 조회 성공"),
                    @ApiResponse(responseCode = "404", description = "interview 정보 없음", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content)
            }
    )
    ResponseEntity<InterviewDetailResponse> getInterviewById(@PathVariable Long interviewId);

}
