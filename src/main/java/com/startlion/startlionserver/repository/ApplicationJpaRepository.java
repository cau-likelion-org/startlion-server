package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    default Application findByIdOrThrow(Long applicationId) {
        return findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 applicationId를 가진 지원서가 존재하지 않습니다."));
    }
    Optional<Application> findByEmail(String email);

    List<Application> findByUserUserId(Long userId);

    List<Application> findByUser(User user);
}
