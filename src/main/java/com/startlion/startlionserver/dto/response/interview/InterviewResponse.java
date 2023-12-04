package com.startlion.startlionserver.dto.response.interview;


import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.dto.response.interviewanswer.InterviewAnswerResponse;

import java.util.List;

public record InterviewResponse(
        Long interviewId,
        String title,
        Long generation,
        String part,
        String major,
        String name,
        String oneLineContent,
        String oneLineAnswer,
        String imageUrl,
        List<InterviewAnswerResponse> interviewAnswers
) {


    //TODO: interviewAnswers, interviewQuestions가 interviewContent로 바뀌었으므로 수정 필요
    // api 호출했을 때, interview의 question과 answer이 같이 나오도록 수정하시면 될 것 같습니다!
    public static InterviewResponse of(Interview interview, List<InterviewAnswerResponse> interviewAnswers) {
        return new InterviewResponse(
                interview.getInterviewId(),
                interview.getTitle(),
                interview.getGeneration(),
                interview.getPart(),
                interview.getMajor(),
                interview.getName(),
                interview.getOneLineContent(),
                interview.getOneLineAnswer(),
                interview.getImageUrl(),
                interviewAnswers
        );
    }
}
