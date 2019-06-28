package cn.ntshare.laboratory.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
public class JWTUtil {

    // 设置token过期时间为5分钟
    private static final long EXPIRE_TIME = 5*60*1000;

    /**
     * 校验Token是否正确
     * @param token     秘钥
     * @param username  用户名
     * @param secret    用户密码
     * @return
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm  algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("token 验证失败");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取token中的username
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }

    /**
     * 生成token，设置过期时间
     * @param username
     * @param secret
     * @return
     */
    public static String sign(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withClaim("username", username).withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("token生成失败");
            e.printStackTrace();
            return null;
        }
    }
}
