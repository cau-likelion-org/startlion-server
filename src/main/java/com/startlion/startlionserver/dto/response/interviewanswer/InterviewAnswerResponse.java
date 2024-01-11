package com.startlion.startlionserver.dto.response.interviewanswer;

import com.startlion.startlionserver.domain.entity.GraduateInterviewContent;
import io.swagger.v3.oas.annotations.media.Schema;

public record InterviewAnswerResponse(
        @Schema(description = "인터뷰 응답 ID")
        Long interviewAnswerId,
        @Schema(description = "질문", example = "자기소개를 해주세요.", required = true)
        String question,

        @Schema(description = "인터뷰 응답 제목")
        String answerTitle,
        @Schema(description = "인터뷰 응답 내용")
        String answerContent

) {

    public static InterviewAnswerResponse of(GraduateInterviewContent interviewContent) {
        return new InterviewAnswerResponse(
                interviewContent.getInterviewAnswerId(),
                interviewContent.getQuestion(),
                interviewContent.getBoldAnswer(),
                interviewContent.getAnswer());
    }
}
