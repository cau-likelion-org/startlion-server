package com.startlion.startlionserver.dto.response.application;


import com.startlion.startlionserver.domain.entity.Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "지원서 페이지 4")
@Builder
public record ApplicationPage4Response(
        @Schema(description = "가능한 면접 시간", example = "0 0 1 0 0 0 1 0 0 1 0 1 0 0 0 1 0 0 1 0 1 0 1 1 1 1")
        String availableInterviewTime
) {
    public static ApplicationPage4Response of(Application application) {
        return ApplicationPage4Response.builder()
                .availableInterviewTime(application.getAvailableInterviewTime())
                .build();
    }
}
