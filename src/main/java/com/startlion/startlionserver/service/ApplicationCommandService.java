package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.request.application.ApplicationPage1Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage2Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage3Request;
import com.startlion.startlionserver.dto.request.application.ApplicationPage4Request;
import com.startlion.startlionserver.dto.response.application.ApplicationPage1Response;
import com.startlion.startlionserver.global.exception.AccessDeniedException;
import com.startlion.startlionserver.repository.ApplicationJpaRepository;
import com.startlion.startlionserver.repository.CurrentGenerationRepository;
import com.startlion.startlionserver.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationCommandService {

    private final UserJpaRepository userJpaRepository;
    private final ApplicationJpaRepository applicationJpaRepository;
    private final CurrentGenerationRepository currentGenerationRepository;
    // method 변수가 되는 것이 아니라 전역 변수가 되어버림.

    public ApplicationPage1Response createApplication(ApplicationPage1Request request, Long userId) {

        val currentGeneration = currentGenerationRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("현재 진행중인 기수가 없습니다."))
                .getGeneration();

        if (!request.isPersonalInformationAgreed()) {
            throw new IllegalArgumentException("개인정보 수집 및 이용에 동의해주세요.");
        }

        val user = userJpaRepository.findByIdOrThrow(userId);
        val application = Application.create(request, user, currentGeneration);
        applicationJpaRepository.save(application);
        return ApplicationPage1Response.of(application);
    }

    public void updateApplicationPage1(Long applicationId, ApplicationPage1Request request, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        application.updateApplicationPage1(request);
    }

    public void updateApplicationPage2(Long applicationId, ApplicationPage2Request request, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        application.updateApplicationPage2(request);
    }

    public void updateApplicationPage3(Long applicationId, ApplicationPage3Request request, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        application.updateApplicationPage3(request);
    }

    public void updateApplicationPage4(Long applicationId, ApplicationPage4Request request, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        application.updateApplicationPage4(request);
    }

    public void submitApplication(Long applicationId, Long userId) {
        val application = applicationJpaRepository.findByIdOrThrow(applicationId);
        checkApplicationOwner(application, userId);
        application.completeApplication();
    }

    // 본인의 지원서인지 체크
    private void checkApplicationOwner(Application application, Long userId){
        val user =  userJpaRepository.findByIdOrThrow(userId);
        if (user.equals(application.getUser())) {
            throw new AccessDeniedException("해당 지원서의 소유자가 아닙니다.");
        }
    }

}