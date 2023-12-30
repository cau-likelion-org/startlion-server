package com.startlion.startlionserver.dto.response.interview;


import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.dto.response.interviewanswer.InterviewAnswerResponse;

import java.util.List;

public record InterviewDetailResponse(
        Long interviewId,
        Long generation,
        String part,
        String name,
        String major,
        String oneLineAnswer,
        String imageUrl,
        List<InterviewAnswerResponse> interviewAnswers
) {

    public static InterviewDetailResponse of(Interview interview, List<InterviewAnswerResponse> interviewAnswers) {
        return new InterviewDetailResponse(
                interview.getInterviewId(),
                interview.getGeneration(),
                interview.getPart(),
                interview.getName(),
                interview.getMajor(),
                interview.getOneLineAnswer(),
                interview.getImageUrl(),
                interviewAnswers
        );
    }
}
