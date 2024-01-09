package com.startlion.startlionserver.dto.response.interviewanswer;

import com.startlion.startlionserver.domain.entity.InterviewContent;
import io.swagger.v3.oas.annotations.media.Schema;

public record InterviewAnswerResponse(
        Long interviewAnswerId,
        @Schema(description = "질문", example = "자기소개를 해주세요.", required = true)
        String question,
        String boldAnswer,
        String answer

) {

    public static InterviewAnswerResponse of(InterviewContent interviewContent) {
        return new InterviewAnswerResponse(
                interviewContent.getInterviewAnswerId(),
                interviewContent.getQuestion(),
                interviewContent.getBoldAnswer(),
                interviewContent.getAnswer());
    }
}
