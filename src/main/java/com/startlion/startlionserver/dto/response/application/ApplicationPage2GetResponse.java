package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.CommonQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApplicationPage2GetResponse {
    private Answer answer;
    private CommonQuestion generation;
    public static ApplicationPage2GetResponse of(Answer answer, CommonQuestion generation) {
        return new ApplicationPage2GetResponse(answer, generation);
    }
}
