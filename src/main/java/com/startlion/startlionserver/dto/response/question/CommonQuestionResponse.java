package com.startlion.startlionserver.dto.response.question;

import com.startlion.startlionserver.domain.entity.CommonQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonQuestionResponse {
    private Long commonQuestionId;
    private Long generation;
    private String commonQuestion1;
    private String commonQuestion2;
    private String commonQuestion3;
    private String commonQuestion4;
    private String commonQuestion5;

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
