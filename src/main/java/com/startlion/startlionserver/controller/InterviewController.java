package com.startlion.startlionserver.controller;


import com.startlion.startlionserver.controller.swagger.InterviewApi;
import com.startlion.startlionserver.dto.response.interview.InterviewDetailResponse;
import com.startlion.startlionserver.dto.response.interview.InterviewResponse;
import com.startlion.startlionserver.service.InterviewService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interviews")
public class InterviewController implements InterviewApi {

    private final InterviewService interviewService;


    // ALL, DEV, DESIGN, BE, FE, PM
    @GetMapping
    public ResponseEntity<List<InterviewResponse>> getInterviews(@RequestParam(required = false) String part) {
        val response = interviewService.getInterviews(part);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewDetailResponse> getInterviewById(@PathVariable Long interviewId) {
        val response = interviewService.getInterviewById(interviewId);
        return ResponseEntity.ok(response);
    }
}
