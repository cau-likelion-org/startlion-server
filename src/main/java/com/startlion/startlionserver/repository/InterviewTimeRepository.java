package com.startlion.startlionserver.repository;

import com.startlion.startlionserver.domain.entity.Application;
import com.startlion.startlionserver.domain.entity.InterviewTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewTimeRepository extends JpaRepository<InterviewTime, Long> {
    void deleteAllByApplication(Application application);
}