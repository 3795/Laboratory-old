package cn.ntshare.laboratory.bo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
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

    public Boolean validateToken(String token, UserDetails userDetails) {
        UserDetailImpl user = (UserDetailImpl) userDetails;
        String username = this.getUsernameFromToken(token);
        Date created = this.getCreateDateFromToken(token);
        return (username.equals(user.getUsername()) &&
                !(this.isTokenExpired(token)) &&
                !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordRest())));
    }

    /**
     * 获取封装在token中的时间
     * @param token
     * @return
     */
    public Date getCreateDateFromToken(String token) {
        final Claims claims = this.getClaimsFromToken(token);
        return new Date((Long) claims.get("created"));
    }

    /**
     * 获取封装在token中过期时间
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        final Claims claims = this.getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断Token是否过期
     * @param token
     * @return
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    /**
     * 判断token是否在最后一次修改密码之前生成的，在最后一次密码修改之前生成的token也是无效的
     * @param created
     * @param lastPasswordReset
     * @return
     */
    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
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
