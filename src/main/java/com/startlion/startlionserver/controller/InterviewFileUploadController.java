package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.service.InterviewFileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/interviews")
@RequiredArgsConstructor
@Tag(name = "[Interview] 인터뷰 파일 업로드 관련 API")
public class InterviewFileUploadController {

    private final InterviewFileUploadService interviewFileUploadService;

    @Operation(summary = "파일 업로드")
    @PostMapping("{interviewId}/file")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file, @PathVariable Long interviewId) {
        interviewFileUploadService.uploadFile(file, interviewId);
        return ResponseEntity.noContent().build();
    }
}
