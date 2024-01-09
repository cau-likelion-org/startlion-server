package com.startlion.startlionserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;


@Tag(name = "[Interview] 인터뷰 파일 업로드 관련 API")
public interface InterviewFileUploadApi {

    @Operation(summary = "파일 업로드")
    ResponseEntity<String> uploadFile(@RequestPart MultipartFile file, @PathVariable Long interviewId);
}
