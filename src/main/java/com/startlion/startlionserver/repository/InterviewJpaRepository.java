package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewJpaRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByPart(String part);
}
