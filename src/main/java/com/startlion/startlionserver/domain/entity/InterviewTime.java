package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class InterviewTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "interview_time_time", joinColumns = @JoinColumn(name = "interview_time_id"))
    @Column(name = "time")
    private List<Integer> time;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private Application application;

    @Builder
    private InterviewTime(List<Integer> time, Application application) {
        this.time = time;
        this.application = application;
    }
}
