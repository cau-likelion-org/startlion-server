package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Application;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ApplicationJpaRepository extends JpaRepository<Application, Long> {

    @EntityGraph(attributePaths = {"answer", "generation", "part"}) // application을 가져올 때 answer, generation, part를 같이 가져옴
    Optional<Application> findById(Long id);

    Optional<Application> findByEmail(String email);

    List<Application> findByUserUserId(Long userId);
}
