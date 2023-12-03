package com.startlion.startlionserver.dto.request.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ApplicationPage4PutRequest {
    @Schema(description = "면접 시간", example = "true", required = true)
    private String interview;
}
