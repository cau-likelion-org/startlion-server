package com.startlion.startlionserver.dto.response.application;

import com.startlion.startlionserver.domain.entity.CommonQuestion;
import com.startlion.startlionserver.domain.entity.Part;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationListGetResponse {

    private Long generationId;

    private String name;

    private Long partId;

    private String status;

    public static ApplicationListGetResponse of(Long generationId, String name, Long partId, String status) {
        return new ApplicationListGetResponse(generationId, name, partId, status);
    }
}
