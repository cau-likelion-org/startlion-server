package com.startlion.startlionserver.dto.response.interviewanswer;

import com.startlion.startlionserver.domain.entity.InterviewAnswer;

public record InterviewAnswerResponse(
        Long interviewAnswerId,
        String answer
) {

    public static InterviewAnswerResponse of(InterviewAnswer interviewAnswer) {
        return new InterviewAnswerResponse(
                interviewAnswer.getInterviewAnswerId(),
                interviewAnswer.getAnswer());
    }
}
