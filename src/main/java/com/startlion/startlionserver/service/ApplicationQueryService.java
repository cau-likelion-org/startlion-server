package com.startlion.startlionserver.service;


import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.response.application.*;
import com.startlion.startlionserver.dto.response.partQuestion.PartQuestionResponse;
import com.startlion.startlionserver.dto.response.question.CommonQuestionResponse;
import com.startlion.startlionserver.global.exception.AccessDeniedException;
import com.startlion.startlionserver.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.startlion.startlionserver.dto.response.question.CommonQuestionResponse.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationQueryService {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PartQuestionJpaRepository partQuestionJpaRepository;
    private final CommonQuestionJpaRepository commonQuestionRepository;
    private final CurrentGenerationRepository currentGenerationRepository;

    public ApplicationPage1Response getApplicationPage1(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        return ApplicationPage1Response.of(application);
    }

    public ApplicationPage2GetResponse getApplicationPage2(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        val currentGeneration = getCurrentGeneration();
        val commonQuestion = commonQuestionRepository.findByGenerationOrThrow(currentGeneration);
        val commonQuestionResponse = of(commonQuestion);
        val applicationPage2Response = ApplicationPage2Response.of(application);
        return ApplicationPage2GetResponse.of(commonQuestionResponse, applicationPage2Response);
    }

    public ApplicationPage3GetResponse getApplicationPage3(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        val currentGeneration = getCurrentGeneration();
        val partQuestion = partQuestionJpaRepository.findByPartAndGenerationOrThrow(application.getPart(), currentGeneration);
        val partQuestionResponse = PartQuestionResponse.of(partQuestion);
        val applicationPage3Response = ApplicationPage3Response.of(application);
        return ApplicationPage3GetResponse.of(applicationPage3Response, partQuestionResponse);
    }

    public ApplicationPage4Response getApplicationPage4(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        return ApplicationPage4Response.of(application);
    }

    public ApplicationGetResponse getApplication(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        val currentGeneration = getCurrentGeneration();
        val partQuestion = partQuestionJpaRepository.findByPartAndGenerationOrThrow(application.getPart(), currentGeneration);
        val commonQuestion = commonQuestionRepository.findByGenerationOrThrow(currentGeneration);

        return ApplicationGetResponse.of(
                ApplicationResponse.of(application),
                of(commonQuestion),
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

    private int getCurrentGeneration() {
        return currentGenerationRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("현재 진행중인 세대가 없습니다."))
                .getGeneration();
    }

    private void checkApplicationOwner(Application application, Long userId){
        val user =  userJpaRepository.findByIdOrThrow(userId);
        if (user.equals(application.getUser())) {
            throw new AccessDeniedException("해당 지원서의 소유자가 아닙니다.");
        }
    }
}
