package com.startlion.startlionserver.controller;

import com.startlion.startlionserver.service.InterviewFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/interviews")
@RequiredArgsConstructor
public class InterviewFileUploadController {

    private final InterviewFileUploadService interviewFileUploadService;

    @PostMapping("{interviewId}/file")
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile file, @PathVariable Long interviewId) {
        interviewFileUploadService.uploadFile(file, interviewId);
        return ResponseEntity.noContent().build();
    }
}
