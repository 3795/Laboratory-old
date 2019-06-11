package cn.ntshare.laboratory.bo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/6/7
 */
public class UserDetailImpl implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Date lastPasswordRest;
    private Collection<? extends GrantedAuthority> authorities;
    private Boolean enable;

    // 定义UserDetails必要的属性， 设置为true时不检查这三项，默认为通过
    private Boolean accountNonExpired = true;
    private Boolean accountNonLocked = true;
    private Boolean credentialsNonExpired = true;

    public UserDetailImpl() {
        super();
    }

    public UserDetailImpl(Long id, String username, String password, Date lastPasswordRest, Collection<? extends GrantedAuthority> authorities, Boolean enable) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastPasswordRest = lastPasswordRest;
        this.enable = enable;
        this.authorities = authorities;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }


    public boolean isEnabled() {
        return this.enable;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastPasswordRest() {
        return lastPasswordRest;
    }

    public void setLastPasswordRest(Date lastPasswordRest) {
        this.lastPasswordRest = lastPasswordRest;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
