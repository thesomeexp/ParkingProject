package com.someexp.common.utils;

import com.someexp.config.prop.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author someexp
 * @date 2020/9/23
 */
@Component
public class JwtUtil {

    private static JwtProperties jwtProperties;

    @Autowired
    public JwtUtil(JwtProperties jwtProperties) {
        JwtUtil.jwtProperties = jwtProperties;
    }

    /**
     * 根据用户id创建JWT
     *
     * @param userId
     * @return
     */
    public static String create(Long userId) {
        return Jwts.builder()
                .claim("id", userId)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpire()))
                .signWith(SignatureAlgorithm.HS512, DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))
                .compact();
    }

    /**
     * 解析token
     *
     * @param jwt
     * @return
     */
    public static Claims decode(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        Claims claims;
        claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.getSecret()))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 根据内容与类型获取参数
     *
     * @param jwt
     * @return
     */
    public static Long getIdByToken(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        Claims claims = decode(jwt);
        return claims.get("id", Long.class);
    }

    public static JwtProperties getJwtProperties() {
        return jwtProperties;
    }

}
