package com.startlion.startlionserver.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum IntervieweePart {
    PM("PM"),
    FE("FE"),
    BE("BE"),
    DESIGN("DESIGN"),
    DEV("DEV");

    private final String name;

    public static List<IntervieweePart> getAllPart() {
        return List.of(PM, FE, BE, DESIGN, DEV);
    }
}
