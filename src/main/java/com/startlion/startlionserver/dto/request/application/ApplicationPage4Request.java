package com.startlion.startlionserver.dto.request.application;

import io.swagger.v3.oas.annotations.media.Schema;


public record ApplicationPage4Request(
    @Schema(description = "최종 제출 여부", example = "true")
    boolean isSubmit,
    @Schema(description = "면접 시간", example = "0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 1")
    String availableInterviewTime
) {

}
