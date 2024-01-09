package com.startlion.startlionserver.dto.response.answer;

import com.startlion.startlionserver.domain.entity.Answer;

public record PartAnswerGetResponse(
        String partAnswer1,
        String partAnswer2,
        String partAnswer3
) {

    public static PartAnswerGetResponse of(Answer answer) {
        return new PartAnswerGetResponse(
                answer.getPartAnswer1(),
                answer.getPartAnswer2(),
                answer.getPartAnswer3());
    }
}
