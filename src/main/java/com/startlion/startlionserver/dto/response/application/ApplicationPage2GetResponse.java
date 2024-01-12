package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.CommonQuestion;
import com.startlion.startlionserver.dto.response.partQuestion.PartQuestionResponse;
import com.startlion.startlionserver.dto.response.question.CommonQuestionResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "지원서 페이지 2 질문, 응답 데이터")
@Builder
public record ApplicationPage2GetResponse(
        CommonQuestionResponse commonQuestion,
        ApplicationPage2Response applicationPage2
) {

    public static ApplicationPage2GetResponse of(
            CommonQuestionResponse commonQuestionResponse,
            ApplicationPage2Response applicationPage2Response) {
        return ApplicationPage2GetResponse.builder()
                .commonQuestion(commonQuestionResponse)
                .applicationPage2(applicationPage2Response)
                .build();
    }
}
