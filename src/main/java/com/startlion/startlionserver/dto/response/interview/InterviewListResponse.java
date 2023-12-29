package com.startlion.startlionserver.dto.response.interview;

import java.util.List;

public record InterviewListResponse(
        List<InterviewDetailResponse> interviews
) {
}
