package com.example.guanli.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 签名密钥
    private static final String SECRET_KEY = "secretkey";
    // 过期时间：12小时（毫秒）
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000L;

    // Deleted:public static String generateJwt(String username) {
    // Deleted:    return Jwts.builder()
    // Deleted:            .setSubject(username)
    // Deleted:            .setIssuedAt(new java.util.Date())
    // Deleted:            .setExpiration(new java.util.Date(System.currentTimeMillis() + 86400000))
    // Deleted:            .signWith(SignatureAlgorithm.HS256, "secretkey")
    // Deleted:            .compact();
    // Deleted:}

    /**
     * 生成JWT令牌
     *
     * @param claims 自定义载荷，如id、username
     * @return 加密后的JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param token JWT令牌
     * @return 载荷数据；令牌被篡改或过期时会抛出异常
     */
    public static Claims parseJwt(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}