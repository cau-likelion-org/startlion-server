package com.startlion.startlionserver.service;


import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.response.application.ApplicationGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationsGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplyApplicationGetResponse;
import com.startlion.startlionserver.dto.response.partQuestion.PartQuestionResponse;
import com.startlion.startlionserver.dto.response.question.CommonQuestionResponse;
import com.startlion.startlionserver.global.exception.AccessDeniedException;
import com.startlion.startlionserver.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationQueryService {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PartQuestionJpaRepository partQuestionJpaRepository;
    private final CommonQuestionJpaRepository commonQuestionRepository;
    private final CurrentGenerationRepository currentGenerationRepository;

    public ApplicationGetResponse getApplication(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        val currentGeneration = currentGenerationRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("현재 진행중인 세대가 없습니다."))
                .getGeneration();

        val partQuestion = partQuestionJpaRepository.findByPartAndGenerationOrThrow(application.getPart(), currentGeneration);
        val commonQuestion = commonQuestionRepository.findByGenerationOrThrow(currentGeneration);

        return ApplicationGetResponse.of(
                ApplicationResponse.of(application),
                CommonQuestionResponse.of(commonQuestion),
                PartQuestionResponse.of(partQuestion));
    }
    public ApplicationsGetResponse getApplications(Long userId) {
        val user = userJpaRepository.findByIdOrThrow(userId);
        val applyApplicationsResponse =  applicationJpaRepository.findByUser(user)
                .stream()
                .map(ApplyApplicationGetResponse::of)
                .toList();
        return ApplicationsGetResponse.of(applyApplicationsResponse);
    }

    private void checkApplicationOwner(Application application, Long userId){
        val user =  userJpaRepository.findByIdOrThrow(userId);
        if (user.equals(application.getUser())) {
            throw new AccessDeniedException("해당 지원서의 소유자가 아닙니다.");
        }
    }
}
