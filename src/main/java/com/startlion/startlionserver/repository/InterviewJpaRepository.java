package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface InterviewJpaRepository extends JpaRepository<Interview, Long> {
}
