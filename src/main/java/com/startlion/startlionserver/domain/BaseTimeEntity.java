package com.startlion.startlionserver.domain;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseTimeEntity {

    @CreatedDate // 생성되는 시간에 업데이트
    private LocalDateTime createdAt; // created_at

    @LastModifiedDate // 업데이트 되는 시간에 업데이트
    private LocalDateTime updatedAt; // updated_at
}