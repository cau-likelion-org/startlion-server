package com.startlion.startlionserver.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplyPart {
    DEFAULT(""),
    BE("백엔드"),
    FE("프론트엔드"),
    DESIGN("디자인"),
    PM("기획");
    private final String name;

    public static ApplyPart of(String name) {
        for (ApplyPart applyPart : ApplyPart.values()) {
            if (applyPart.name.equals(name)) {
                return applyPart;
            }
        }
        return DEFAULT;
    }
}
