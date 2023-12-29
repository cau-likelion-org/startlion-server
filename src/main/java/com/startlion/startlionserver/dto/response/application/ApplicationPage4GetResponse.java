package com.startlion.startlionserver.dto.response.application;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationPage4GetResponse {
    private List<List<Integer>> interview;
    public static ApplicationPage4GetResponse of(List<List<Integer>> interview) {
        return new ApplicationPage4GetResponse(interview);
    }
}
