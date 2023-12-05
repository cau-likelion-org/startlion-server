package com.startlion.startlionserver.dto.response.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationListGetResponse {

    private Long applicationId;

    private Long generationId;

    private String name;

    private Long partId;

    private String status;

    public static ApplicationListGetResponse of(Long applicationId, Long generationId, String name, Long partId, String status) {
        return new ApplicationListGetResponse(applicationId ,generationId, name, partId, status);
    }
}
