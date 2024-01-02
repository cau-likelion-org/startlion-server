package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.CommonQuestion;
import com.startlion.startlionserver.dto.response.answer.CommonAnswerGetResponse;
import com.startlion.startlionserver.dto.response.question.CommonQuestionResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class ApplicationPage2GetResponse {
    @Schema(description = "답변")
    private CommonAnswerGetResponse answer;
    @Schema(description = "질문")
    private CommonQuestionResponse commonquestion;

    public ApplicationPage2GetResponse(CommonAnswerGetResponse answer, CommonQuestionResponse generation) {
        this.answer = answer;
        this.commonquestion = generation;
    }

    public static ApplicationPage2GetResponse of(Answer answer, CommonQuestion generation) {
        return new ApplicationPage2GetResponse(CommonAnswerGetResponse.of(answer), CommonQuestionResponse.of(generation));
    }
}
