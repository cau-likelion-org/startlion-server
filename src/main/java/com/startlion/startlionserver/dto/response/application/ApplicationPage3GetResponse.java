package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PartQuestion;
import com.startlion.startlionserver.dto.response.answerGetResponse.PartAnswerGetResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationPage3GetResponse {

    private PartAnswerGetResponse answer;
    private List<PartQuestion> partQuestions;
    private String portfolio;
    public static ApplicationPage3GetResponse of(Answer answer, List<PartQuestion> partQuestions, String portfolio) {
        return new ApplicationPage3GetResponse(new PartAnswerGetResponse(answer), partQuestions, portfolio);
    }
}
