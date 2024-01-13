package com.startlion.startlionserver.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Semester {
    DEFAULT(""),
    FIRST("1-1"),
    SECOND("1-2"),
    THIRD("2-1"),
    FOURTH("2-2"),
    FIFTH("3-1"),
    SIXTH("3-2"),
    SEVENTH("4-1"),
    EIGHTH("4-2"),
    EXCEED_SEMESTER("초과 학기"),
    // 휴학
    VACATION("휴학 예정");

    private final String name;

}
