package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    default Application findByIdOrThrow(Long applicationId){
        return findById(applicationId).orElseThrow(() -> new IllegalArgumentException());
    };

    default Application findByIdOrCreate(Long applicationId){
        return findById(applicationId).orElse(save(Application.builder().build()));
    }

    Optional<Application> findByEmail(String email);
}
