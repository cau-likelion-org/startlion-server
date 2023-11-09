package com.startlion.startlionserver.dto.response.application;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationGetWithInterviewResponse {
    private String interview;
    public static ApplicationGetWithInterviewResponse of(String interview) {
        return new ApplicationGetWithInterviewResponse(interview);
    }
}
