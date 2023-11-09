package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationGetWithPartQuestionAndAnswerAndPortfolioResponse {

    private Answer answer;
    private Part part;
    private String portfolio;
    public static ApplicationGetWithPartQuestionAndAnswerAndPortfolioResponse of(Answer answer, Part part, String portfolio) {
        return new ApplicationGetWithPartQuestionAndAnswerAndPortfolioResponse(answer, part, portfolio);
    }
}
