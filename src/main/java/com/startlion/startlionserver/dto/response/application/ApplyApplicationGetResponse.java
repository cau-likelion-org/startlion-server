package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "지원서 조회 응답")
public record ApplyApplicationGetResponse(
        Long applicationId,
        int generation,
        String name,
        String part,
        String status
) {

    public static ApplyApplicationGetResponse of(Application application) {
        return new ApplyApplicationGetResponse(
                application.getApplicationId(),
                application.getGeneration(),
                application.getName(),
                application.getPart().getName(),
                application.getStatus().toString()
        );
    }
}
