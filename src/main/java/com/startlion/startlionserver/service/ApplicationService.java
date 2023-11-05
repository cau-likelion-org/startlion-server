package com.startlion.startlionserver.service;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.response.application.ApplicationGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationPersonalInformationGetResponse;
import com.startlion.startlionserver.repository.ApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicationService {

    private final ApplicationJpaRepository applicationJpaRepository;

    public ApplicationGetResponse getById(Long applicationId){
        Application application = applicationJpaRepository.findByIdOrThrow(applicationId);
        return ApplicationGetResponse.of(application);
    }

    public ApplicationPersonalInformationGetResponse getApplicationPersonalInformation() {
        return ApplicationPersonalInformationGetResponse.builder().build();
    }

    public ApplicationGetResponse updateApplication(Long applicationId) {
        return null;
    }
}
