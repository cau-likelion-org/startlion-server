package com.startlion.startlionserver.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GoogleLoginResponse {
    private String access_token;
    private String expires_in;
    private String refreshToken;
    private String scope;
    private String id_token;
}