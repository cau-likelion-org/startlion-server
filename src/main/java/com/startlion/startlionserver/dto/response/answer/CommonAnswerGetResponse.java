package com.startlion.startlionserver.dto.response.answer;

import com.startlion.startlionserver.domain.entity.Answer;

public record CommonAnswerGetResponse(
        String commonAnswer1,
        String commonAnswer2,
        String commonAnswer3,
        String commonAnswer4,
        String commonAnswer5
) {
    public static CommonAnswerGetResponse of(Answer answer) {
        return new CommonAnswerGetResponse(
                answer.getCommonAnswer1(),
                answer.getCommonAnswer2(),
                answer.getCommonAnswer3(),
                answer.getCommonAnswer4(),
                answer.getCommonAnswer5()
        );
    }
}