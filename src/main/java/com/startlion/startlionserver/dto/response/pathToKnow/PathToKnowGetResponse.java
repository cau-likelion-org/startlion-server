package com.startlion.startlionserver.dto.response.pathToKnow;

import com.startlion.startlionserver.domain.entity.PathToKnow;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PathToKnowGetResponse {
    private String pathType;
    private Long pathToKnowId;

    public PathToKnowGetResponse(PathToKnow pathToKnow) {
        this.pathType = pathToKnow.getPathType().name();
        this.pathToKnowId = pathToKnow.getPathToKnowId();
    }
}
