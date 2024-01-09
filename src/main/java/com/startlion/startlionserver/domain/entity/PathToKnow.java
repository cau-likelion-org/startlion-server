package com.startlion.startlionserver.domain.entity;

import com.startlion.startlionserver.domain.PathType;
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
    private Application application;

    @Enumerated(EnumType.STRING)
    private PathType pathType;

    private String etcDetail;

    public void updateApplication(Application application) {
        this.application = application;
    }
}
