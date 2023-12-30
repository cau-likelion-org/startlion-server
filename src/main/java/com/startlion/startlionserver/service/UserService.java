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
        boolean isSubmittedExists = checkIsSubmitted(applications);

        // boolean을 String으로 변환
        String isSubmitted = boolToString(isSubmittedExists);

        // 지원서 리스트 가져오기
        List<ApplicationListGetResponse> applicationList = loadApplicationList(applications);

        // null값 지원서
        ApplicationListGetResponse defaultApplication = ApplicationListGetResponse.of(null,null,null,null,null);


        return new ApplicationListWithSubmittedResponse(isSubmitted, applicationList, defaultApplication);
    }

    // 제출된 지원서 있는지 체크
    private boolean checkIsSubmitted(List<Application> applications){
        boolean isSubmitted = applications.stream()
                .anyMatch(application -> "Y".equals(application.getStatus()));
        return isSubmitted;
    }

    // boolean을 String으로 변환
    private String boolToString(boolean bool){
        return bool ? "Y" : "N";
    }

    // 지원서 리스트 불러오기
    private List<ApplicationListGetResponse> loadApplicationList(List<Application> applications){
        List<ApplicationListGetResponse> applicationList = applications.stream()
                .map(application -> ApplicationListGetResponse.of(
                        application.getApplicationId(),
                        application.getGeneration().getCommonQuestionId(),
                        application.getName(),
                        application.getPart().getPartId(),
                        application.getStatus()))
                .collect(Collectors.toList());

        return applicationList;
    }
}
