package com.startlion.startlionserver.dto.response.interview;


import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.dto.response.interviewanswer.InterviewAnswerResponse;

import java.util.List;

public record InterviewResponse(
        Long interviewId,
        Long generation,
        String name,
        String part,
        String OneLineContent
) {

    public static InterviewResponse of(Interview interview) {
        return new InterviewResponse(
                interview.getInterviewId(),
                interview.getGeneration(),
                interview.getName(),
                interview.getPart(),
                interview.getOneLineContent()
        );
    }
}
