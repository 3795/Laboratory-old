package cn.ntshare.laboratory.token;

import cn.ntshare.laboratory.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {

    private static final long serialVersionUID = -1532419329816501299L;

    // 秘钥
    private String token;

    private String username;

    public JWTToken(String token) {
        this.token = token;
        this.username = JWTUtil.getUsername(token);
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
