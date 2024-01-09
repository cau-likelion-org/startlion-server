package com.startlion.startlionserver.dto.response.partQuestion;

import com.startlion.startlionserver.domain.entity.PartQuestion;
public record PartQuestionResponse(
        Long questionId,
        String part,
        Long generation,
        String partQuestion1,
        String partQuestion2,
        String partQuestion3
) {

    public static PartQuestionResponse of(PartQuestion partQuestion) {
        return new PartQuestionResponse(
                partQuestion.getQuestionId(),
                partQuestion.getPart().getKoreanName(),
                partQuestion.getGeneration(),
                partQuestion.getPartQuestion1(),
                partQuestion.getPartQuestion2(),
                partQuestion.getPartQuestion3());
    }
}
