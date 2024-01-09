package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.PartQuestion;
import com.startlion.startlionserver.dto.response.answer.PartAnswerGetResponse;
import com.startlion.startlionserver.dto.response.partQuestion.PartQuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ApplicationPage3GetResponse {

    private PartAnswerGetResponse answer;
    private List<PartQuestionResponse> partQuestions;
    private String portfolio;
    public static ApplicationPage3GetResponse of(Answer answer, List<PartQuestion> partQuestions, String portfolio) {
        List<PartQuestionResponse> partQuestionResponses = partQuestions.stream()
                .map(PartQuestionResponse::of)
                .collect(Collectors.toList());
        return new ApplicationPage3GetResponse(PartAnswerGetResponse.of(answer), partQuestionResponses, portfolio);
    }
}
