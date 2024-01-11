package com.startlion.startlionserver.dto.response.interview;


import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.dto.response.interviewanswer.InterviewAnswerResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Schema(description = "인터뷰 상세 페이지 데이터")
public record InterviewDetailResponse(
        @Schema(description = "인터뷰 ID")
        Long interviewId,

        @Schema(description = "활동 기수")
        Long generation,

        @Schema(description = "인터뷰 대상자 지원 파트")
        String part,

        @Schema(description = "인터뷰 대상자 이름")
        String name,

        @Schema(description = "인터뷰 대상자 전공")
        String major,

        @Schema(description = "인터뷰 대상자 image url")
        String imageUrl,
        List<InterviewAnswerResponse> interviewAnswers
) {

    public static InterviewDetailResponse of(Interview interview, List<InterviewAnswerResponse> interviewAnswers) {
        return new InterviewDetailResponse(
                interview.getInterviewId(),
                interview.getGeneration(),
                interview.getPart().toString(),
                interview.getName(),
                interview.getMajor(),
                interview.getImageUrl(),
                interviewAnswers
        );
    }
}
