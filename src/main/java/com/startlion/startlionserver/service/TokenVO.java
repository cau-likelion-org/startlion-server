package com.startlion.startlionserver.service;


public record TokenVO(
        String accessToken,
        String refreshToken
) {

    public TokenVO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
