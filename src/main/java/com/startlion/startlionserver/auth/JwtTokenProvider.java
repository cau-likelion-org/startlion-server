package com.startlion.startlionserver.auth;

import com.startlion.startlionserver.config.jwt.JwtValidationType;
import com.startlion.startlionserver.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret) {
        JWT_SECRET = Base64.getEncoder().encodeToString(secret.getBytes(StandardCharsets.UTF_8));
    }

    // 토큰 생성
    public String generateToken(Authentication authentication, Long tokenExpirationTime) {
        final Date now = new Date();

        // 클레임 생성
        final Claims claims = Jwts.claims()
                .setIssuedAt(now)               // 발급 시간
                .setExpiration(new Date(now.getTime() + tokenExpirationTime));      // 만료 시간

        // getPrincipal: 인증 주체(principal)를 반환, 보통 인증된 사용자를 의미. 주로 사용자의 식별자(ID)나 사용자 객체(User)를 포함
        claims.put("memberId", authentication.getPrincipal());

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")       // 암호화 설정 알고리즘 및 토큰 타입 사용, JWT의 메타데이터를 정의하며, 토큰의 유효성 검증이나 처리 시 필요한 정보를 제공
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, getSigningKey())    // 서명 추가 메서드.(토큰의 무결성과 신뢰성을 보장하기 위함)
                .compact();                                             // JWT를 문자열로 직렬화되어 변환
    }

    private byte[] getSigningKey() {
        return JWT_SECRET.getBytes(StandardCharsets.UTF_8);
    }

    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getBody(token);
            return JwtValidationType.VALID_JWT;
        } catch (Exception ex) {
            if (ex instanceof io.jsonwebtoken.security.SecurityException) {
                return JwtValidationType.INVALID_JWT_SIGNATURE;
            } else if (ex instanceof io.jsonwebtoken.MalformedJwtException) {
                return JwtValidationType.INVALID_JWT_TOKEN;
            } else if (ex instanceof io.jsonwebtoken.ExpiredJwtException) {
                return JwtValidationType.EXPIRED_JWT_TOKEN;
            } else if (ex instanceof io.jsonwebtoken.UnsupportedJwtException) {
                return JwtValidationType.UNSUPPORTED_JWT_TOKEN;
            } else {
                return JwtValidationType.EMPTY_JWT;
            }
        }
    }

    private Claims getBody(final String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get("memberId").toString());       // 클레임에서 memberId라는 이름의 클레임 값 추출 -> String으로 변환 -> Long타입으로 변경
    }
}


//package com.startlion.startlionserver.auth;
//import com.startlion.startlionserver.domain.entity.User;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @Value("${jwt.accessTokenExpiration}")
//    private long accessTokenExpiredTime;
//
//    @Value("${jwt.refreshTokenExpiration}")
//    private long refreshTokenExpiredTime;
//    private long expiredTime;
//    public String generateToken(Authentication authentication,String tokentype) {
//
//        switch (tokentype) {
//            case "access":
//                expiredTime = accessTokenExpiredTime;
//                break;
//            case "refresh":
//                expiredTime = refreshTokenExpiredTime;
//                break;
//        }
//        User user = (User) authentication.getPrincipal();
//        Date now = new Date();
//
//        Date accessTokenValidity = new Date(now.getTime() + expiredTime);
//
//        return Jwts.builder()
//                .setSubject(user.getUsername())
//                .setIssuedAt(now)
//                .setExpiration(accessTokenValidity)
//                .signWith(SignatureAlgorithm.HS256, secretKey)
//                .compact();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String getUsernameFromToken(String token) {
//        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }
//}