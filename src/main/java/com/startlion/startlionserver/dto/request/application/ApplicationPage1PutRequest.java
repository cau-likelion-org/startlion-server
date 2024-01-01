package com.startlion.startlionserver.dto.request.application;

import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PathToKnow;
import com.startlion.startlionserver.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.List;

@Data
@AllArgsConstructor
public class ApplicationPage1PutRequest {
    @Schema(description = "동의 여부", example = "true", required = true)
    private Boolean isAgreed;

    @Schema(description = "이름", example = "park", required = true)
    private String name;

    @Schema(description = "성별", example = "M", required = true)
    private String gender;

    @Schema(description = "학번", example = "20190315", required = true)
    private Integer studentNum;

    @Schema(description = "전공", example = "econ", required = true)
    private String major;

    @Schema(description = "복수전공", example = "cs", required = true)
    private String multiMajor;

    @Schema(description = "학기", example = "3-2", required = true)
    private String semester;

    @Schema(description = "전화번호", example = "010-1234-5678", required = true)
    private String phone;

    @Schema(description = "이메일", example = "123@gmail.com", required = true)
    private String email;

    @Schema(description = "알게 된 경로", required = true)
    private List<PathToKnow> pathToKnows;

    @Schema(description = "파트", required = true)
    private Part part;

    @Schema(description = "유저", required = true)
    private User user;

    public boolean getIsAgreed() {
        return isAgreed == null ? false : isAgreed;
    }
}
