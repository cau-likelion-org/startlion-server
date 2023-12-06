package com.startlion.startlionserver.service;


import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.dto.response.application.ApplicationListGetResponse;
import com.startlion.startlionserver.dto.response.application.ApplicationListWithSubmittedResponse;
import com.startlion.startlionserver.repository.ApplicationJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final ApplicationJpaRepository applicationJpaRepository;

    public ApplicationListWithSubmittedResponse getApplicationList(Long userId) {
        List<Application> applications = applicationJpaRepository.findByUserUserId(userId);

        // applications 리스트를 순회하면서 status가 "Y"인 application이 있는지 확인
        boolean isSubmittedExists = applications.stream()
                .anyMatch(application -> "Y".equals(application.getStatus()));

        // boolean을 String으로 변환
        String isSubmitted = isSubmittedExists ? "Y" : "N";

        List<ApplicationListGetResponse> applicationList = applications.stream()
                .map(application -> ApplicationListGetResponse.of(
                        application.getApplicationId(),
                        application.getGeneration().getCommonQuestionId(),
                        application.getName(),
                        application.getPart().getPartId(),
                        application.getStatus()))
                .collect(Collectors.toList());

        return new ApplicationListWithSubmittedResponse(isSubmitted, applicationList);
    }
}
