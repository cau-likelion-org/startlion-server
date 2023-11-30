package com.startlion.startlionserver.dto.response.interview;


import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.domain.entity.InterviewAnswer;
import com.startlion.startlionserver.dto.response.interviewanswer.InterviewAnswerResponse;

import java.util.List;

public record InterviewResponse(
        Long interviewId,
        String title,
        Long generation,
        String part,
        String major,
        String name,
        String oneLineContent,
        String oneLineAnswer,
        String imageUrl,
        List<InterviewAnswerResponse> interviewAnswers
) {

    public static InterviewResponse of(Interview interview, List<InterviewAnswerResponse> interviewAnswers) {
        return new InterviewResponse(
                interview.getInterviewId(),
                interview.getTitle(),
                interview.getGeneration(),
                interview.getPart(),
                interview.getMajor(),
                interview.getName(),
                interview.getOneLineContent(),
                interview.getOneLineAnswer(),
                interview.getImageUrl(),
                interviewAnswers
        );
    }
}
