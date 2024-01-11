package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Interview;
import com.startlion.startlionserver.dto.response.interview.InterviewDetailResponse;
import com.startlion.startlionserver.dto.response.interview.InterviewResponse;
import com.startlion.startlionserver.dto.response.interviewanswer.InterviewAnswerResponse;
import com.startlion.startlionserver.repository.InterviewAnswerJpaRepository;
import com.startlion.startlionserver.repository.InterviewJpaRepository;
import com.startlion.startlionserver.repository.InterviewQueryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InterviewService {

    private final InterviewJpaRepository interviewJpaRepository;
    private final InterviewAnswerJpaRepository interviewAnswerJpaRepository;
    private final InterviewQueryRepository interviewQueryRepository;

    public InterviewDetailResponse getInterviewById(Long interviewId) {
         Interview interview = interviewJpaRepository.findById(interviewId)
                 .orElseThrow( () -> new IllegalArgumentException("해당하는 인터뷰가 없습니다."));

          List<InterviewAnswerResponse> interviewAnswerResponses = interviewAnswerJpaRepository.findByInterview(interview)
                  .stream()
                  .map(InterviewAnswerResponse::of)
                  .toList();
          return InterviewDetailResponse.of(interview, interviewAnswerResponses);
    }

    public List<InterviewResponse> getInterviews(String part) {
            return interviewQueryRepository.findAllByPart(part).stream()
                .map(InterviewResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateInterviewImageUrl(Long interviewId, String imageUrl) {
        Interview interview = getById(interviewId);
        interview.updateImageUrl(imageUrl);
    }

    protected Interview getById(Long interviewId) {
        return interviewJpaRepository.findById(interviewId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 인터뷰가 없습니다."));
    }
}
