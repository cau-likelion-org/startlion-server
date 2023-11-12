package com.startlion.startlionserver.auth;
import com.startlion.startlionserver.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class jwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.accessTokenExpiration}")
    private long accessTokenExpiredTime;

    @Value("${jwt.refreshTokenExpiration}")
    private long refreshTokenExpiredTime;
    private long expiredTime;
    public String generateToken(Authentication authentication,String tokentype) {

        switch (tokentype) {
            case "access":
                expiredTime = accessTokenExpiredTime;
                break;
            case "refresh":
                expiredTime = refreshTokenExpiredTime;
                break;
        }
        User user = (User) authentication.getPrincipal();
        Date now = new Date();

        Date accessTokenValidity = new Date(now.getTime() + expiredTime);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(accessTokenValidity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
