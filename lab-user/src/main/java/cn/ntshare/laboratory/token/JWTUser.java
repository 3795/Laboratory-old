package cn.ntshare.laboratory.token;

import lombok.Data;

import java.io.Serializable;

@Data
public class JWTUser implements Serializable {

    private static final long serialVersionUID = -3043635288525685894L;

    private String account;

    private String token;

}
