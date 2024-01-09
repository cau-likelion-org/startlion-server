package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.response.pathToKnow.PathToKnowGetResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.val;

import java.util.List;
import java.util.stream.Collectors;

@Schema(description = "지원서 1페이지 조회 응답")
public record ApplicationPage1GetResponse(
        @Schema(description = "개인정보 수집 및 이용 동의 여부")
        Boolean isAgreed,
        @Schema(description = "이름")
        String name,
        @Schema(description = "성별")
        String gender,
        @Schema(description = "학번")
        int studentNum,
        @Schema(description = "전공")
        String major,
        @Schema(description = "복수전공")
        String multiMajor,
        @Schema(description = "학기")
        String semester,
        @Schema(description = "전화번호")
        String phone,
        @Schema(description = "이메일")
        String email,
        @Schema(description = "지원경로")
        List<PathToKnowGetResponse> pathToKnows,
        @Schema(description = "지원파트")
        String part
) {

    public static ApplicationPage1GetResponse createNoArgs() {
        return new ApplicationPage1GetResponse(
                null,
                null,
                null,
                0,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public static ApplicationPage1GetResponse of(Application application) {
        // 리스트 형태로 PathToKnow 생성
        List<PathToKnowGetResponse> pathToKnowResponses = application.getPathToKnows().stream()
                .map(PathToKnowGetResponse::new)
                .collect(Collectors.toList());
        // Part의 한글 이름 직접 사용
        val part = application.getPart().getKoreanName();
        return new ApplicationPage1GetResponse(
                application.getIsAgreed(),
                application.getName(),
                application.getGender(),
                application.getStudentNum(),
                application.getMajor(),
                application.getMultiMajor(),
                application.getSemester(),
                application.getPhone(),
                application.getEmail(),
                pathToKnowResponses,
                part);
    }
}
