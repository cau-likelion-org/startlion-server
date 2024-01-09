package com.startlion.startlionserver.dto.request.application;

import io.swagger.v3.oas.annotations.media.Schema;

public record ApplicationPage2PutRequest(
    @Schema(description = "공통 질문 답변1", example = "true", required = true)
    String commonAnswer1,
    @Schema(description = "공통 질문 답변2", example = "true", required = true)
    String commonAnswer2,
    @Schema(description = "공통 질문 답변3", example = "true", required = true)
    String commonAnswer3,
    @Schema(description = "공통 질문 답변4", example = "true", required = true)
    String commonAnswer4,
    @Schema(description = "공통 질문 답변5", example = "true", required = true)
    String commonAnswer5
) {

}
