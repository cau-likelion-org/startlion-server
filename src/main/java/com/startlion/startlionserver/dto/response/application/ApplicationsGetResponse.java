package com.startlion.startlionserver.dto.response.application;

import java.util.List;

public record ApplicationsGetResponse(
        List<ApplyApplicationGetResponse> applications

) {
    public static ApplicationsGetResponse of(List<ApplyApplicationGetResponse> applications) {
        return new ApplicationsGetResponse(applications);
    }
}
