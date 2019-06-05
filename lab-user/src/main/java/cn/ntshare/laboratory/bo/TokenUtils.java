package cn.ntshare.laboratory.bo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    private String secret = "secret";

    private String expiration = "604800";

    /**
     * 根据 TokenDetail 生成Token
     * @param tokenDetail
     * @return
     */
    public String generationToken(TokenDetail tokenDetail) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", tokenDetail.getUsername());
        claims.put("created", this.generateCurrentDate());
        return this.generateToken(claims);
    }

    // 根据claims生成Token
    private String generateToken(Map<String, Object> claims) {
        try {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret.getBytes("utf-8"))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(this.generateExpirationDate())
                    .signWith(SignatureAlgorithm.HS512, this.secret)
                    .compact();
        }
    }

    /**
     * 从Token中获取username
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        final Claims claims = this.getClaimsFromToken(token);
        username = claims.getSubject();
        return username;
    }

    /**
     * 解析Token的主体Claims
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret.getBytes("UTF-8"))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedEncodingException e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成过期时间
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + Long.parseLong(this.expiration) * 1000);
    }
    /**
     * 获取当前时间
     * @return
     */
    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }
}
