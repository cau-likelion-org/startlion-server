package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table
public class PathToKnow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long PathToKnowId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PathType pathType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    public enum PathType {
        RECOMMENDATION,
        INSTAGRAM,
        EVERYTIME,
        HOMEPAGE,
        ETC
    }
}
