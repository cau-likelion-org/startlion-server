package com.startlion.startlionserver.dto.response.interview;

import java.util.List;

public record InterviewListResponse(
        List<InterviewResponse> interviews
) {
}
