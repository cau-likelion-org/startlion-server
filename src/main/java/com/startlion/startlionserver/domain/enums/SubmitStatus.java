package com.startlion.startlionserver.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubmitStatus {
    COMPLETE("지원완료"),
    PROGRESSING("지원중");

    private final String name;
}
