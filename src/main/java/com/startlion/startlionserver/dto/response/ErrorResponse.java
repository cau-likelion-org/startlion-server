package com.startlion.startlionserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}
