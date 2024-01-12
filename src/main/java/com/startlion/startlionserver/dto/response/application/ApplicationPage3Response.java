package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
public record ApplicationPage3Response(
        @Schema(description = "파트")
        String part,

        @Schema(description = "파트별 질문 1 답변")
        String partAnswer1,
        @Schema(description = "파트별 질문 2 답변")
        String partAnswer2,
        @Schema(description = "파트별 질문 3 답변")
        String partAnswer3,
        @Schema(description = "파트별 질문 4 답변")
        String partAnswer4
) {

    public static ApplicationPage3Response of(Application application) {
        return ApplicationPage3Response.builder()
                .part(application.getPart().getName())
                .partAnswer1(application.getPartAnswer1())
                .partAnswer2(application.getPartAnswer2())
                .partAnswer3(application.getPartAnswer3())
                .partAnswer4(application.getPartAnswer4())
                .build();
    }
}
