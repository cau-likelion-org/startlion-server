package com.startlion.startlionserver.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IntervieweePart {
    PM("PM"),
    FE("FE"),
    BE("BE"),
    DESIGN("DESIGN"),
    DEV("DEV");

    private final String name;
}
