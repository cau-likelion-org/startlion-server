package com.startlion.startlionserver.service;


import lombok.Data;

@Data
public class TokenVO {
    private String accessToken;
    private String refreshToken;

    public TokenVO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
