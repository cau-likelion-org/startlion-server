package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.CommonQuestion;
import com.startlion.startlionserver.dto.response.answer.CommonAnswerGetResponse;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApplicationPage2GetResponse {
    private CommonAnswerGetResponse answer;
    private CommonQuestion generation;
    public static ApplicationPage2GetResponse of(Answer answer, CommonQuestion generation) {
        return new ApplicationPage2GetResponse(new CommonAnswerGetResponse(answer), generation);
    }
}
