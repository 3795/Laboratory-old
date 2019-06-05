package cn.ntshare.laboratory.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 角色ID
    private Integer roleId;

    // 是否启用该账号，做黑名单
    private Byte enable;

    // 最近修改密码时间
    private Long lastPasswordChange;

    private BigDecimal amount;

    // 拥有的权限名称
    @Transient
    private String auth;
}
