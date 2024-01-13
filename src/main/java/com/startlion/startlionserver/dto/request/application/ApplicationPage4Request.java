package com.startlion.startlionserver.dto.request.application;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Schema(description = "지원서 페이지 4 요청")
public record ApplicationPage4Request(
    @Schema(description = "면접 가능 날짜 첫번째", example = "[0,0,0,0,0,0,0,0,0,0]")
    List<Integer> firstDay,
    @Schema(description = "면접 가능 날짜 두번쨰", example = "[0,0,0,0,0,0,0,0,0,0]")
    List<Integer> secondDay,
    @Schema(description = "면접 가능 날짜 세번쨰", example = "[0,0,0,0,0,0,0,0,0,0]")
    List<Integer> thirdDay,
    @Schema(description = "최종 제출 여부", example = "true")
    boolean lastCheck

    ) {

}
