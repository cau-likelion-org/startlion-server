package com.startlion.startlionserver.dto.response.application;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationPage4GetResponse {
    private String interview;
    public static ApplicationPage4GetResponse of(String interview) {
        return new ApplicationPage4GetResponse(interview);
    }
}
