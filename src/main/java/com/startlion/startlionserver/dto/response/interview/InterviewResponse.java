package com.startlion.startlionserver.dto.response.interview;


import com.startlion.startlionserver.domain.entity.Interview;

public record InterviewResponse(
        Long interviewId,
        Long generation,
        String name,
        String part,
        String OneLineContent,
        String imageUrl
) {

    public static InterviewResponse of(Interview interview) {
        return new InterviewResponse(
                interview.getInterviewId(),
                interview.getGeneration(),
                interview.getName(),
                interview.getPart(),
                interview.getOneLineContent(),
                interview.getImageUrl()
        );
    }
}
