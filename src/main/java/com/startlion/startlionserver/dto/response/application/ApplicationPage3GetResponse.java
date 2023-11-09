package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Answer;
import com.startlion.startlionserver.domain.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationPage3GetResponse {

    private Answer answer;
    private Part part;
    private String portfolio;
    public static ApplicationPage3GetResponse of(Answer answer, Part part, String portfolio) {
        return new ApplicationPage3GetResponse(answer, part, portfolio);
    }
}
