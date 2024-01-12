package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.dto.response.partQuestion.PartQuestionResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "지원서 페이지 3 질문, 응답 데이터")
@Builder
public record ApplicationPage3GetResponse(
        ApplicationPage3Response applicationPage3,
        PartQuestionResponse partQuestion
) {

    public static ApplicationPage3GetResponse of(
            ApplicationPage3Response applicationPage3Response,
            PartQuestionResponse partQuestionResponse) {
        return ApplicationPage3GetResponse.builder()
                .applicationPage3(applicationPage3Response)
                .partQuestion(partQuestionResponse)
                .build();
    }
}
