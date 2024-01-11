package com.startlion.startlionserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String code; // 에러 코드
    private String message; // 에러 메시지 JSON 형식 지정

    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message);
    }
}
