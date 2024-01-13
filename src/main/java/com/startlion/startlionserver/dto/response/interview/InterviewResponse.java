package com.startlion.startlionserver.dto.response.interview;


import com.startlion.startlionserver.domain.entity.GraduateInterview;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "인터뷰 목록 페이지 데이터")
public record InterviewResponse(
        @Schema(description = "인터뷰 ID", example = "1") Long interviewId,

        @Schema(description = "활동 기수", example = "12") Long generation,

        @Schema(description = "인터뷰 대상자 지원 파트", example = "기획") String part,

        @Schema(description = "인터뷰 대상자 이름", example = "홍길동") String name,

        @Schema(description = "인터뷰 목록 페이지에서 사용하는 섬네일 텍스트") String thumbnailText,

        @Schema(description = "인터뷰 대상자 imageUrl") String imageUrl
) {

    public static InterviewResponse of(GraduateInterview graduateInterview) {
        return new InterviewResponse(
                graduateInterview.getInterviewId(),
                graduateInterview.getGeneration(),
                graduateInterview.getPart().toString(),
                graduateInterview.getName(),
                graduateInterview.getOneLineContent(),
                graduateInterview.getImageUrl()
        );
    }
}
