package com.startlion.startlionserver.dto.response.question;

import com.startlion.startlionserver.domain.entity.CommonQuestion;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "공통 질문 데이터")
public record CommonQuestionResponse(
        @Schema(description = "공통 질문 ID") Long commonQuestionId,
        @Schema(description = "공통질문 1번") String commonQuestion1,
        @Schema(description = "공통질문 2번") String commonQuestion2,
        @Schema(description = "공통질문 3번") String commonQuestion3,
        @Schema(description = "공통질문 4번") String commonQuestion4,
        @Schema(description = "공통질문 5번") String commonQuestion5
) {

    public static CommonQuestionResponse of(CommonQuestion commonQuestion) {
        return new CommonQuestionResponse(
                commonQuestion.getCommonQuestionId(),
                commonQuestion.getCommonQuestion1(),
                commonQuestion.getCommonQuestion2(),
                commonQuestion.getCommonQuestion3(),
                commonQuestion.getCommonQuestion4(),
                commonQuestion.getCommonQuestion5()
        );
    }
}
