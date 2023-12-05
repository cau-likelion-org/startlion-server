package com.startlion.startlionserver.dto.request.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationPage2PutRequest {
    @Schema(description = "공통 질문 답변1", example = "true", required = true)
    private String commonAnswer1;
    @Schema(description = "공통 질문 답변2", example = "true", required = true)
    private String commonAnswer2;
    @Schema(description = "공통 질문 답변3", example = "true", required = true)
    private String commonAnswer3;
    @Schema(description = "공통 질문 답변4", example = "true", required = true)
    private String commonAnswer4;
    @Schema(description = "공통 질문 답변5", example = "true", required = true)
    private String commonAnswer5;
}
