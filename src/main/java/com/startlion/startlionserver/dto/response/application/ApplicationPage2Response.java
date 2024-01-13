package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;


@Builder
@Schema(description = "지원서 페이지 2 응답")
public record ApplicationPage2Response(
        @Schema(description = "공통 질문 1 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
        String commonAnswer1,
        @Schema(description = "공통 질문 2 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
        String commonAnswer2,
        @Schema(description = "공통 질문 3 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
        String commonAnswer3,
        @Schema(description = "공통 질문 4 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
        String commonAnswer4,
        @Schema(description = "공통 질문 5 답변", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat.")
        String commonAnswer5
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
