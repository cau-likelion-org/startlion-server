package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table
@Setter
public class PathToKnow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long PathToKnowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    @JsonIgnore
    private Application application;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PathType pathType;

    public enum PathType {
        RECOMMENDATION,
        INSTAGRAM,
        EVERYTIME,
        HOMEPAGE,
        ETC
    }
}
