package com.startlion.startlionserver.service;


import lombok.Builder;

public record TokenVO(
        String accessToken,
        String refreshToken
) {
    public static TokenVO of(String accessToken, String refreshToken) {
        return new TokenVO(accessToken, refreshToken);
    }
}
