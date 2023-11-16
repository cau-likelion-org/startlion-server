package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.dto.response.pathToKnow.PathToKnowGetResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    private List<PathToKnowGetResponse> pathToKnows;

    private Part part;

    public static ApplicationPage1GetResponse of(Application application) {
        List<PathToKnowGetResponse> pathToKnowResponses = application.getPathToKnows().stream()
                .map(PathToKnowGetResponse::new)
                .collect(Collectors.toList());
        return new ApplicationPage1GetResponse(application.getIsAgreed(), application.getName(), application.getGender(), application.getStudentNum(), application.getMajor(), application.getMultiMajor(), application.getSemester(), application.getPhone(), application.getEmail(), pathToKnowResponses, application.getPart());}

}
