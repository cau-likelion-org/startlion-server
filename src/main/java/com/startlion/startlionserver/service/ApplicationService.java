package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.request.application.ApplicationTemporaryStorageRequest;
import com.startlion.startlionserver.dto.response.application.ApplicationGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPersonalInformationGetResponse;
import com.startlion.startlionserver.global.exception.EmailAlreadyInUseException;
import com.startlion.startlionserver.repository.ApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationJpaRepository applicationJpaRepository;

    public ApplicationGetResponse getById(Long applicationId){
        Application application = applicationJpaRepository.findByIdOrThrow(applicationId);
        return ApplicationGetResponse.of(application);
    }

    // 저장된 지원서 없을 시, 지원서 1페이지 정보 가져오기
    public ApplicationPersonalInformationGetResponse getApplicationPersonalInformation() {
        return ApplicationPersonalInformationGetResponse.builder().build();
    }

    // 지원서 임시 저장 or 다음 버튼 누를 시 table 생성 또는 update
    @Transactional
    public String updateApplication(Long applicationId, ApplicationTemporaryStorageRequest request) {
        Application application;
        Optional<Application> optionalApplication = applicationJpaRepository.findById(applicationId);

        // email 중복 확인
        Optional<Application> optionalApplicationWithEmail = applicationJpaRepository.findByEmail(request.getEmail());
        if (optionalApplicationWithEmail.isPresent() && !optionalApplicationWithEmail.get().getApplicationId().equals(applicationId)) {
            throw new EmailAlreadyInUseException("email is already in use '" + request.getEmail() + "'");
        }

        // applicationId가 존재하면 update, 존재하지 않으면 create
        if (optionalApplication.isPresent()) {
            application = optionalApplication.get();
        } else {
            application = Application.builder().build();
            applicationJpaRepository.save(application);
        }

        application.updateApplication(request.getIsAgreed(), request.getName(), request.getGender(), request.getStudentNum(), request.getMajor(), request.getMultiMajor(), request.getSemester(), request.getPhone(), request.getEmail(), request.getPathToKnow(), request.getPart());

        return application.getApplicationId().toString();
    }
}
