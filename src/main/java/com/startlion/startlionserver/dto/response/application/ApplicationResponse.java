package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "지원서 상세 조회 응답")
public record ApplicationResponse(
        @Schema(description = "지원서 ID")
        Long applicationId,
        @Schema(description = "이름")
        String name,
        @Schema(description = "성별")
        Gender gender,
        @Schema(description = "학번")
        String studentNumber,
        @Schema(description = "전공")
        String major,
        @Schema(description = "복수전공")
        String multiMajor,
        @Schema(description = "학기")
        String semester,
        @Schema(description = "전화번호")
        String phone,
        @Schema(description = "이메일")
        String email,
        @Schema(description = "포트폴리오 URL")
        String portfolioUrl,
        @Schema(description = "지원서 상태")
        String status,
        @Schema(description = "가능한 인터뷰 시간")
        String availableInterviewTime,
        @Schema(description = "공통 질문 1")
        String commonAnswer1,
        @Schema(description = "공통 질문 2")
        String commonAnswer2,
        @Schema(description = "공통 질문 3")
        String commonAnswer3,
        @Schema(description = "공통 질문 4")
        String commonAnswer4,
        @Schema(description = "공통 질문 5")
        String commonAnswer5,
        @Schema(description = "파트별 질문 1")
        String partAnswer1,
        @Schema(description = "파트별 질문 2")
        String partAnswer2,
        @Schema(description = "파트별 질문 3")
        String partAnswer3,
        @Schema(description = "파트별 질문 4")
        String partAnswer4
) {
    public static ApplicationResponse of(Application application) {
        return new ApplicationResponse(
                application.getApplicationId(),
                application.getName(),
                application.getGender(),
                application.getStudentNumber(),
                application.getMajor(),
                application.getMultiMajor(),
                application.getSemester().getName(),
                application.getPhone(),
                application.getEmail(),
                application.getPortfolioUrl(),
                application.getStatus().toString(),
                application.getAvailableInterviewTime(),
                application.getCommonAnswer1(),
                application.getCommonAnswer2(),
                application.getCommonAnswer3(),
                application.getCommonAnswer4(),
                application.getCommonAnswer5(),
                application.getPartAnswer1(),
                application.getPartAnswer2(),
                application.getPartAnswer3(),
                application.getPartAnswer4()
        );
    }
}
