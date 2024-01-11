package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.dto.response.partQuestion.PartQuestionResponse;
import com.startlion.startlionserver.dto.response.question.CommonQuestionResponse;

public record ApplicationGetResponse(
        ApplicationResponse application,
        CommonQuestionResponse commonQuestion,
        PartQuestionResponse partQuestion
) {

    public static ApplicationGetResponse of(
            ApplicationResponse applicationResponse,
            CommonQuestionResponse commonQuestionResponse,
            PartQuestionResponse partQuestionResponse
    ) {
        return new ApplicationGetResponse(applicationResponse, commonQuestionResponse, partQuestionResponse);
    }
}
