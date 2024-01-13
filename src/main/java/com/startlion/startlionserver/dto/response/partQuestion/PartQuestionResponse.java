package com.startlion.startlionserver.dto.response.partQuestion;

import com.startlion.startlionserver.domain.entity.PartQuestion;
import io.swagger.v3.oas.annotations.media.Schema;

public record PartQuestionResponse(
        @Schema(description = "질문 ID")
        Long questionId,
        @Schema(description = "파트 이름")
        String part,
        @Schema(description = "파트별 질문 1")
        String partQuestion1,
        @Schema(description = "파트별 질문 2")
        String partQuestion2,
        @Schema(description = "파트별 질문 3")
        String partQuestion3,

        @Schema(description = "파트별 질문 4")
        String partQuestion4
) {

    public static PartQuestionResponse of(PartQuestion partQuestion) {
        return new PartQuestionResponse(
                partQuestion.getQuestionId(),
                partQuestion.getPart(),
                partQuestion.getPartQuestion1(),
                partQuestion.getPartQuestion2(),
                partQuestion.getPartQuestion3(),
                partQuestion.getPartQuestion4())
        ;
    }
}
