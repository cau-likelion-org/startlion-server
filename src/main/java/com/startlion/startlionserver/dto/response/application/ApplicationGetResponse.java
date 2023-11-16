package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.util.List;


@Data
@AllArgsConstructor
public class ApplicationGetResponse {
    private Answer answer;

    private CommonQuestion generation;

    private Boolean isAgreed;

    private String name;

    private String gender;

    private Integer studentNum;

    private String major;

    private String multiMajor;

    private String semester;

    private String phone;

    private String email;

    private List<PathToKnow> pathToKnows;

    private Part part;

    private String portfolio;

    private String interview;

    private String status;

    public static ApplicationGetResponse of(Application application){
        return new ApplicationGetResponse(application.getAnswer(), application.getGeneration(), application.getIsAgreed(), application.getName(), application.getGender(), application.getStudentNum(), application.getMajor(), application.getMultiMajor(), application.getSemester(), application.getPhone(), application.getEmail(), application.getPathToKnows(), application.getPart(), application.getPortfolio(), application.getInterview(), application.getStatus());
    }
}
