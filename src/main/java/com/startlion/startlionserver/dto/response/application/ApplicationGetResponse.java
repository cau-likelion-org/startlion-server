package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PathToKnow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor // check
@Builder
public class ApplicationGetResponse {

    // application page 1 start
    private boolean isAgreed;

    private String name;

    private String gender;

    private Integer studentNum;

    private String major;

    private String multiMajor;

    private Integer semester;

    private String phone;

    private String email;

    private List<PathToKnow> pathToKnow;

    private Part part;
    // application page 1 end


    private String portfolio;

    private String interview;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static ApplicationGetResponse of(Application application) {
        return new ApplicationGetResponse(application.isAgreed(), application.getName(), application.getGender(), application.getStudentNum(), application.getMajor(), application.getMultiMajor(), application.getSemester(), application.getPhone(), application.getEmail(), application.getPathToKnow(), application.getPart(), application.getPortfolio(), application.getInterview(), application.getStatus(), application.getCreatedAt(), application.getUpdatedAt());}
}