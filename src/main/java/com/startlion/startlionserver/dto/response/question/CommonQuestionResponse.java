package com.startlion.startlionserver.dto.response.question;

import com.startlion.startlionserver.domain.entity.CommonQuestion;


public record CommonQuestionResponse(
        Long commonQuestionId,
        Long generation,
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
