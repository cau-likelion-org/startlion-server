package com.startlion.startlionserver.service;


import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.response.application.*;
import com.startlion.startlionserver.dto.response.partQuestion.PartQuestionResponse;
import com.startlion.startlionserver.global.exception.AccessDeniedException;
import com.startlion.startlionserver.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.startlion.startlionserver.dto.response.question.CommonQuestionResponse.of;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationQueryService {

    private static final int FIRST_DAY_LAST_INTERVIEW_INDEX = 11;
    private static final int SECOND_DAY_LAST_INTERVIEW_INDEX = 23;

    private final ApplicationJpaRepository applicationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final PartQuestionJpaRepository partQuestionJpaRepository;
    private final CommonQuestionJpaRepository commonQuestionRepository;

    @Value("${current-generation}")
    private int currentGeneration;

    public ApplicationPage1Response getApplicationPage1(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        return ApplicationPage1Response.of(application);
    }

    public ApplicationPage2Response getApplicationPage2(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        return ApplicationPage2Response.of(application);
    }

    public ApplicationPage3Response getApplicationPage3(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        return ApplicationPage3Response.of(application);
    }

    public ApplicationPage4Response getApplicationPage4(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        val firstDays = List.of(application.getAvailableInterviewTime1().split(","));
        val secondDays = List.of(application.getAvailableInterviewTime2().split(","));
        val thirdDays =  List.of(application.getAvailableInterviewTime3().split(","));
        return ApplicationPage4Response.of(firstDays, secondDays, thirdDays);
    }

    public ApplicationGetResponse getApplication(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        val partQuestion = partQuestionJpaRepository.findByPartAndGenerationOrThrow(application.getPart().toString(), currentGeneration);
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

    private void checkApplicationOwner(Application application, Long userId){
        val user =  userJpaRepository.findByIdOrThrow(userId);
        if (user.equals(application.getUser())) {
            throw new AccessDeniedException("해당 지원서의 소유자가 아닙니다.");
        }
    }
}
