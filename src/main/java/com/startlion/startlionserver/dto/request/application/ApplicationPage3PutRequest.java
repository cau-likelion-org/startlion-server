package com.startlion.startlionserver.dto.request.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationPage3PutRequest {
    @Schema(description = "파트 질문 답변1", example = "true", required = true)
    private String partAnswer1;

    @Schema(description = "파트 질문 답변2", example = "true", required = true)
    private String partAnswer2;

    @Schema(description = "파트 질문 답변3", example = "true", required = true)
    private String partAnswer3;

    @Schema(description = "포트폴리오", example = "true", required = true)
    private String portfolio;
}
