package com.startlion.startlionserver.dto.response.application;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationListWithSubmittedResponse {
    // 제출된 application이 존재하는지 여부 -> 있으면 "Y", 없으면 "N"(작성된 application이 있어도 N)
    private String isSubmitted;

    // member의 작성 application의 List
    private List<ApplicationListGetResponse> applicationList;
}
