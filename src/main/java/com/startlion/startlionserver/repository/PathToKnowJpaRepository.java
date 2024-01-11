package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.PathToKnow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PathToKnowJpaRepository extends JpaRepository<PathToKnow, Long> {
    List<PathToKnowJpaRepository> findByApplicationId(Long applicationId);

    void deleteAllByApplicationId(Long applicationId);
}
