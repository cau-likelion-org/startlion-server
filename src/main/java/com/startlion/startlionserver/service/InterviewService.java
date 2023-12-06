package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.dto.response.interview.InterviewResponse;
import com.startlion.startlionserver.dto.response.interviewanswer.InterviewAnswerResponse;
import com.startlion.startlionserver.repository.InterviewAnswerJpaRepository;
import com.startlion.startlionserver.repository.InterviewJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {

    private final InterviewJpaRepository interviewJpaRepository;
    private final InterviewAnswerJpaRepository interviewAnswerJpaRepository;

    public InterviewResponse getInterviewById(Long interviewId) {
         Interview interview = interviewJpaRepository.findById(interviewId)
                 .orElseThrow( () -> new IllegalArgumentException("해당하는 인터뷰가 없습니다."));

          List<InterviewAnswerResponse> interviewAnswerResponses = interviewAnswerJpaRepository.findByInterview(interview)
                  .stream()
                  .map(InterviewAnswerResponse::of)
                  .toList();
          return InterviewResponse.of(interview, interviewAnswerResponses);
    }

    public List<InterviewResponse> getInterviews() {
       return interviewJpaRepository.findAll()
                .stream()
                .map(interview ->
                InterviewResponse.of(interview, interviewAnswerJpaRepository.findByInterview(interview)
                .stream()
                .map(InterviewAnswerResponse::of)
                .toList()))
                .toList();
    }

    @Transactional
    public void updateInterviewImageUrl(Long interviewId, String imageUrl) {
        Interview interview = findById(interviewId);
        interview.updateImageUrl(imageUrl);
    }

    protected Interview findById(Long interviewId) {
        return interviewJpaRepository.findById(interviewId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 인터뷰가 없습니다."));
    }


}
