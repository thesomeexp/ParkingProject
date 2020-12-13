package com.someexp.common.utils;

import com.someexp.config.prop.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

/**
 * @author someexp
 * @date 2020/9/23
 */
@Component
public class JwtUtils {

    private static JwtProperties jwtProperties;

    @Autowired
    public JwtUtils(JwtProperties jwtProperties) {
        JwtUtils.jwtProperties = jwtProperties;
    }

    /**
     * 根据用户id创建JWT
     *
     * @param userId
     * @return
     */
    @SneakyThrows
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
    @SneakyThrows
    public static Claims decode(String jwt) {
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
    @SneakyThrows
    public static Long getIdByToken(String jwt) {
        Claims claims = decode(jwt);
        return claims.get("id", Long.class);
    }

    public static JwtProperties getJwtProperties() {
        return jwtProperties;
    }

}
