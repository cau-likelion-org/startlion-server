package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PathToKnow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ApplicationPage1GetResponse {
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

    public static ApplicationPage1GetResponse of(Application application) {
        return new ApplicationPage1GetResponse(application.getIsAgreed(), application.getName(), application.getGender(), application.getStudentNum(), application.getMajor(), application.getMultiMajor(), application.getSemester(), application.getPhone(), application.getEmail(), application.getPathToKnows(), application.getPart());}

}
