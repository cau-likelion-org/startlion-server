package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
@Schema(description = "지원서 페이지 2 응답")
public record ApplicationPage3Response(
        @Schema(description = "파트", example = "기획")
        String part,

        @Schema(description = "파트별 질문 1 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
        String partAnswer1,
        @Schema(description = "파트별 질문 2 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
        String partAnswer2,
        @Schema(description = "파트별 질문 3 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
        String partAnswer3,
        @Schema(description = "파트별 질문 4 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
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
