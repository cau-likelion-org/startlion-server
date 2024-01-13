package com.startlion.startlionserver.controller.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "[Interview] 인터뷰 파일 업로드 관련 API")
public interface InterviewFileUploadApi {

    @Operation(summary = "파일 업로드")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "파일 업로드 성공"),
                    @ApiResponse(responseCode = "404", description = "파일 업로드 실패", content = @Content),
                    @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content)
            }
    )
    ResponseEntity<String> uploadFile(@RequestPart MultipartFile file, @PathVariable Long interviewId);
}
