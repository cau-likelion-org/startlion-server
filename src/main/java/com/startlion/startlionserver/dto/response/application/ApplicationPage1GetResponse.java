package com.startlion.startlionserver.dto.response.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.response.part.PartIdResponse;
import com.startlion.startlionserver.dto.response.pathToKnow.PathToKnowGetResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Schema(description = "지원서 1페이지 조회 응답")
@JsonPropertyOrder({ "isAgreed", "name", "gender", "studentNum", "major", "multiMajor", "semester", "phone", "email", "pathToKnows", "part" })
public class ApplicationPage1GetResponse {
    @Schema(description = "개인정보 수집 및 이용 동의 여부")
    @JsonProperty("isAgreed") // JSON 필드 이름을 'isAgreed'로 설정
    private Boolean isAgreed;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "성별")
    private String gender;
    @Schema(description = "학번")
    private int studentNum;
    @Schema(description = "전공")
    private String major;
    @Schema(description = "복수전공")
    private String multiMajor;
    @Schema(description = "학기")
    private String semester;
    @Schema(description = "전화번호")
    private String phone;
    @Schema(description = "이메일")
    private String email;
    @Schema(description = "지원경로")
    private List<PathToKnowGetResponse> pathToKnows;
    @Schema(description = "지원파트")
    private PartIdResponse part;

    @Builder
    public ApplicationPage1GetResponse(Boolean isAgreed, String name, String gender, Integer studentNum, String major, String multiMajor, String semester, String phone, String email, List<PathToKnowGetResponse> pathToKnows, PartIdResponse part) {
        this.isAgreed = isAgreed;
        this.name = name;
        this.gender = gender;
        this.studentNum = studentNum;
        this.major = major;
        this.multiMajor = multiMajor;
        this.semester = semester;
        this.phone = phone;
        this.email = email;
        this.pathToKnows = pathToKnows;
        this.part = part;
    }

    public static ApplicationPage1GetResponse of(Application application) {
        // 리스트 형태로 PathToKnow 생성
        List<PathToKnowGetResponse> pathToKnowResponses = application.getPathToKnows().stream()
                .map(PathToKnowGetResponse::new)
                .collect(Collectors.toList());
        // PartId만 표시
        PartIdResponse part = new PartIdResponse(application.getPart());
        return new ApplicationPage1GetResponse(application.getIsAgreed(), application.getName(), application.getGender(), application.getStudentNum(), application.getMajor(), application.getMultiMajor(), application.getSemester(), application.getPhone(), application.getEmail(), pathToKnowResponses, part);}
}
