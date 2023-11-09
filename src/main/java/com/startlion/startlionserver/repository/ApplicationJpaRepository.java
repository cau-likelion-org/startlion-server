package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Application;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    @EntityGraph(attributePaths = {"answer", "generation", "part"})
    Optional<Application> findById(Long id);

    default Application findByIdOrThrow(Long applicationId){
        return findById(applicationId).orElseThrow(() -> new IllegalArgumentException());
    };

    Optional<Application> findByEmail(String email);
}
