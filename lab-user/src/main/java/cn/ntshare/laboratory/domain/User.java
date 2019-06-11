package cn.ntshare.laboratory.domain;

import cn.ntshare.laboratory.bo.TokenDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements LoginDetail, TokenDetail {

    @Id
    @GeneratedValue
    private Long id;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 角色名
    private String roleName;

    // 是否启用该账号，做黑名单
    private Byte enable;

    // 最近修改密码时间
    private Date lastPasswordChange = new Date();

    // 拥有的权限名称
    private String auth;

    public Boolean enable() {
        return this.enable == 1;
    }
}
