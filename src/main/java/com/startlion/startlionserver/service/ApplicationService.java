package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.request.application.ApplicationTemporaryStorageRequest;
import com.startlion.startlionserver.dto.response.application.ApplicationPage2GetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPage4GetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPage3GetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPage1GetResponse;;
import com.startlion.startlionserver.global.exception.EmailAlreadyInUseException;
import com.startlion.startlionserver.repository.ApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationJpaRepository applicationJpaRepository;

    // 저장된 지원서 없을 시, 지원서 1페이지 정보 가져오기 OK
    public ApplicationPage1GetResponse getApplicationPersonalInformation() {
        return ApplicationPage1GetResponse.builder().build();
    }

    // 저장된 지원서 있을 시, 지원서 정보 가져오기
    public ResponseEntity<?> getById(Long applicationId, int page) {
        Application application = applicationJpaRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 applicationId를 가진 지원서가 존재하지 않습니다."));
        switch (page) {
            case 1:
                return ResponseEntity.ok(ApplicationPage1GetResponse.of(application));
            case 2:
                return ResponseEntity.ok(ApplicationPage2GetResponse.of(application.getAnswer(), application.getGeneration()));
            case 3:
                return ResponseEntity.ok(ApplicationPage3GetResponse.of(application.getAnswer(), application.getPart(), application.getPortfolio()));
            case 4:
                return ResponseEntity.ok(ApplicationPage4GetResponse.of(application.getInterview()));
            default:
                throw new IllegalArgumentException("페이지 번호가 잘못되었습니다.");
        }
    }

    // 지원서 임시 저장 or 다음 버튼 누를 시 table 생성 또는 update
    @Transactional
    public String updateApplication(Long applicationId, ApplicationTemporaryStorageRequest request, String isFinal) {
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

        application.updateApplication(request.getIsAgreed(), request.getName(), request.getGender(), request.getStudentNum(), request.getMajor(), request.getMultiMajor(), request.getSemester(), request.getPhone(), request.getEmail(), request.getPathToKnows(), request.getPart(), isFinal.equals("true") ? "Y" : "S");


        return application.getApplicationId().toString();
    }
}

