package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
public record ApplicationPage2Response(
        @Schema(description = "공통 질문 1 답변") String commonAnswer1,
        @Schema(description = "공통 질문 2 답변") String commonAnswer2,
        @Schema(description = "공통 질문 3 답변") String commonAnswer3,
        @Schema(description = "공통 질문 4 답변") String commonAnswer4,
        @Schema(description = "공통 질문 5 답변") String commonAnswer5
) {

    public static ApplicationPage2Response of(Application application) {
        return ApplicationPage2Response.builder()
                .commonAnswer1(application.getCommonAnswer1())
                .commonAnswer2(application.getCommonAnswer2())
                .commonAnswer3(application.getCommonAnswer3())
                .commonAnswer4(application.getCommonAnswer4())
                .commonAnswer5(application.getCommonAnswer5())
                .build();
    }
}
