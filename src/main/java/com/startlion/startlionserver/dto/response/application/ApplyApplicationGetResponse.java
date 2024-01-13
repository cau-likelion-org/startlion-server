package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "지원서 조회 응답")
public record ApplyApplicationGetResponse(

        @Schema(description = "지원서 ID", example = "1")
        Long applicationId,

        @Schema(description = "기수", example = "12")
        int generation,

        @Schema(description = "이름", example = "홍길동")
        String name,

        @Schema(description = "파트", example = "백엔드/프론트엔드/디자인/기획")
        String part,

        @Schema(description = "지원서 상태", example = "지원완료, 지원중")
        String status
) {

    public static ApplyApplicationGetResponse of(Application application) {
        return new ApplyApplicationGetResponse(
                application.getApplicationId(),
                application.getGeneration(),
                application.getName(),
                application.getPart().getName(),
                application.getStatus().getName()
        );
    }
}
