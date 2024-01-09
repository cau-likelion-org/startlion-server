package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PathToKnow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long PathToKnowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    @JsonIgnore
    private Application applicationId;

    @Enumerated(EnumType.STRING)
    @Column
    private PathType pathType;

    @Column
    private String etcDetail;

    public enum PathType {
        RECOMMENDATION,
        INSTAGRAM,
        EVERYTIME,
        HOMEPAGE,
        ETC
    }

    public void setApplicationId(Application applicationId) {
        this.applicationId = applicationId;
    }
}
