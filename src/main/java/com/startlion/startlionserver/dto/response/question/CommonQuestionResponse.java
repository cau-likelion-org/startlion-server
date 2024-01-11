package com.startlion.startlionserver.dto.response.question;

import com.startlion.startlionserver.domain.entity.CommonQuestion;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "공통 질문 응답 데이터")
public record CommonQuestionResponse(

        @Schema(description = "공통 질문 ID")
        Long commonQuestionId,

        @Schema(description = "기수")
        Long generation,

        @Schema(description = "공통질문 1번")
        String commonQuestion1,
        String commonQuestion2,
        String commonQuestion3,
        String commonQuestion5,
        String commonQuestion4
) {

    public static CommonQuestionResponse of(CommonQuestion commonQuestion) {
        return new CommonQuestionResponse(
                commonQuestion.getCommonQuestionId(),
                commonQuestion.getGeneration(),
                commonQuestion.getCommonQuestion1(),
                commonQuestion.getCommonQuestion2(),
                commonQuestion.getCommonQuestion3(),
                commonQuestion.getCommonQuestion4(),
                commonQuestion.getCommonQuestion5()
        );
    }
}
