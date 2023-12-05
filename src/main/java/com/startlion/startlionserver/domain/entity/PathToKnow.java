package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table
@Setter
@NoArgsConstructor
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
    @Column()
    @ColumnDefault("")
    private PathType pathType;

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
