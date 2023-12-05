package com.startlion.startlionserver.controller;


import com.startlion.startlionserver.dto.response.interview.InterviewResponse;
import com.startlion.startlionserver.service.InterviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/interviews")
public class InterviewController {

    private final InterviewService interviewService;


    @Operation(summary = "interview 정보 조회")
    @GetMapping
    public ResponseEntity<List<InterviewResponse>> getInterviews() {
        val response = interviewService.getInterviews();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "interviewId로 interview 정보 조회")
    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewResponse> getInterviewById(@PathVariable Long interviewId) {
        val response = interviewService.getInterviewById(interviewId);
        return ResponseEntity.ok(response);
    }
}
