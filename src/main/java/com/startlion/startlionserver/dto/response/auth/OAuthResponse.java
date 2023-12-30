package com.startlion.startlionserver.dto.response.auth;

public record OAuthResponse(
        String accessToken,
        String refreshToken
) {

    public static OAuthResponse of(String accessToken, String refreshToken) {
        return new OAuthResponse(accessToken, refreshToken);
    }

    @Override
    public String toString() {
        return "accessToken=" + accessToken + "&refreshToken=" + refreshToken;
    }
}
