package com.startlion.startlionserver.dto.response.answer;

import com.startlion.startlionserver.domain.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PartAnswerGetResponse {
    private String partAnswer1;
    private String partAnswer2;
    private String partAnswer3;

    public PartAnswerGetResponse (Answer answer) {
        this.partAnswer1 = answer.getPartAnswer1();
        this.partAnswer2 = answer.getPartAnswer2();
        this.partAnswer3 = answer.getPartAnswer3();
    }
}
