package com.startlion.startlionserver.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleLoginResponse {
    private String accessToken;
    private String expiresIn;
    private String refreshToken;
    private String scope;
    private String id_token;
}