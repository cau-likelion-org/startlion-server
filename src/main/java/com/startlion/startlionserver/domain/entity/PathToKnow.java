package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.enums.PathType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PathToKnow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PathToKnowId;
    private Long applicationId;
    @Enumerated(EnumType.STRING)
    private PathType pathType;
    private String etcDetail;

    @Builder

    private PathToKnow(Long applicationId, PathType pathType, String etcDetail) {
        this.applicationId = applicationId;
        this.pathType = pathType;
        this.etcDetail = etcDetail;
    }
}
