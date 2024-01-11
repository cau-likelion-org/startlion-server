package com.startlion.startlionserver.dto.request.application;

import io.swagger.v3.oas.annotations.media.Schema;

public record ApplicationPage3Request(
        @Schema(description = "파트 질문 답변1", example = "true") String partAnswer1,
        @Schema(description = "파트 질문 답변2", example = "true") String partAnswer2,
        @Schema(description = "파트 질문 답변3", example = "true") String partAnswer3,
        @Schema(description = "파트 질문 답변4", example = "true") String partAnswer4,
        @Schema(description = "포트폴리오", example = "true") String portfolioUrl
) {

}
