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
    PLAN("기획");
    private final String name;
}
