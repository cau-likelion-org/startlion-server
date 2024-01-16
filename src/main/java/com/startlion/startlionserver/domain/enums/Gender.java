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

    public static Gender of(String name) {
        for (Gender gender : Gender.values()) {
            if (gender.name.equals(name)) {
                return gender;
            }
        }
        return DEFAULT;
    }
}
