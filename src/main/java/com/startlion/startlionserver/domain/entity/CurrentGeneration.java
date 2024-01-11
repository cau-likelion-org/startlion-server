package com.startlion.startlionserver.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrentGeneration {

    @Id @GeneratedValue
    private Integer id;
    private int generation;

    @Builder
    private CurrentGeneration(int generation) {
        this.generation = generation;
    }
}
