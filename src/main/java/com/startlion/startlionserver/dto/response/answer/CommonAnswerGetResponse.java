package com.startlion.startlionserver.dto.response.answer;

import com.startlion.startlionserver.domain.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommonAnswerGetResponse {
    private String commonAnswer1;
    private String commonAnswer2;
    private String commonAnswer3;
    private String commonAnswer4;
    private String commonAnswer5;

    public CommonAnswerGetResponse(Answer answer) {
        this.commonAnswer1 = answer.getCommonAnswer1();
        this.commonAnswer2 = answer.getCommonAnswer2();
        this.commonAnswer3 = answer.getCommonAnswer3();
        this.commonAnswer4 = answer.getCommonAnswer4();
        this.commonAnswer5 = answer.getCommonAnswer5();
    }

    public static CommonAnswerGetResponse of(Answer answer) {
        return new CommonAnswerGetResponse(answer);
    }
}