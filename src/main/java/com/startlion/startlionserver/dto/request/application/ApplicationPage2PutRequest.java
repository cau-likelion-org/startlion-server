package com.startlion.startlionserver.dto.request.application;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationPage2PutRequest {
    private String commonAnswer1;
    private String commonAnswer2;
    private String commonAnswer3;
    private String commonAnswer4;
    private String commonAnswer5;
}
