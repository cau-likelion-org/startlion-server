package com.startlion.startlionserver.dto.request.application;

import com.startlion.startlionserver.domain.enums.Gender;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ApplicationCreateRequest(
        @NotNull
        @Schema(description = "개인정보 제공 이용 동의여부", example = "1") boolean isPersonalInformationAgreed,
        @NotNull
        @Schema(description = "성함", example = "홍길동") String name,
        @NotNull
        @Schema(description = "성별", example = "M") Gender gender,
        @NotNull
        @Schema(description = "학번", example = "20190315") String studentNumber,
        @NotNull
        @Schema(description = "전공", example = "소프트웨어학부") String major,
        @Schema(description = "복수전공", example = "수학") String multiMajor,
        @Schema(description = "학기", example = "3-2") String semester,
        @Schema(description = "전화번호", example = "010-1234-5678") String phone,

        @Email
        @Schema(description = "이메일", example = "puang@gmail.com") String email,
        @Schema(description = "알게 된 경로") List<String> pathToKnows,
        @Schema(description = "기타 선택시에 알게 된 경로", example = "인터넷 검색") String etcDetail,
        @Schema(description = "파트", example = "기획") String part
) {
}
