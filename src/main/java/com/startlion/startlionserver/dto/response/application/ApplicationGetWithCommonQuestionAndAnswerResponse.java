package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.CommonQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApplicationGetWithCommonQuestionAndAnswerResponse {
    private Answer answer;
    private CommonQuestion generation;
    public static ApplicationGetWithCommonQuestionAndAnswerResponse of(Answer answer, CommonQuestion generation) {
        return new ApplicationGetWithCommonQuestionAndAnswerResponse(answer, generation);
    }
}
