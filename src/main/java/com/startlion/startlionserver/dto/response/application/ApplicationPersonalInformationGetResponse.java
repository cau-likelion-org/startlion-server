package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PathToKnow;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ApplicationPersonalInformationGetResponse {
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
}
