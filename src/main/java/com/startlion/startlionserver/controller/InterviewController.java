package com.startlion.startlionserver.controller;


import com.startlion.startlionserver.dto.response.interview.InterviewDetailResponse;
import com.startlion.startlionserver.dto.response.interview.InterviewResponse;
import com.startlion.startlionserver.service.InterviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/interviews")
@Tag(name = "[Interview] 인터뷰 관련 API")
public class InterviewController {

    private final InterviewService interviewService;


    @Operation(summary = "interview 정보 조회")
    @GetMapping
    public ResponseEntity<List<InterviewResponse>> getInterviews(@RequestParam(required = false) String part) {
        val response = interviewService.getInterviews(part);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "interviewId로 interview 정보 조회")
    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewDetailResponse> getInterviewById(@PathVariable Long interviewId) {
        val response = interviewService.getInterviewById(interviewId);
        return ResponseEntity.ok(response);
    }
}
