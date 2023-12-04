package com.startlion.startlionserver.service;


import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.response.application.ApplicationListGetResponse;
import com.startlion.startlionserver.repository.ApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final ApplicationJpaRepository applicationJpaRepository;

    public List<ApplicationListGetResponse> getApplicationList(Long userId) {
        List<Application> applications = applicationJpaRepository.findByUserUserId(userId);

        return applications.stream()
                .map(application -> ApplicationListGetResponse.of(
                        application.getGeneration().getCommonQuestionId(),
                        application.getName(),
                        application.getPart().getPartId(),
                        application.getStatus()))
                .collect(Collectors.toList());
    }
}
