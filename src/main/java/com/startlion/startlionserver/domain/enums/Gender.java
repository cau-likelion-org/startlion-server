package com.startlion.startlionserver.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    DEFAULT(""),
    M("male"),
    F("female");

    private final String name;
}
