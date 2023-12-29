package com.startlion.startlionserver.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table
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

    public InterviewTime(List<Integer> time, Application application) {
        this.time = time;
        this.application = application;
    }
}
