package com.startlion.startlionserver.dto.request.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationPage3PutRequest {
    private String partAnswer1;
    private String partAnswer2;
    private String partAnswer3;
    private String portfolio;
}
