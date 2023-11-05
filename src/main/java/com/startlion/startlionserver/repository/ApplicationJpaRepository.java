package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.domain.entity.Part;
import com.startlion.startlionserver.domain.entity.PathToKnow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    default Application findByIdOrThrow(Long applicationId){
        return findById(applicationId).orElseThrow(() -> new IllegalArgumentException());
    };
}
