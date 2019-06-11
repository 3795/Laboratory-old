package cn.ntshare.laboratory.bo;

import cn.ntshare.laboratory.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/6/7
 */
public class SecurityModelFactory {

    public static UserDetailImpl create(User user) {
        Collection<? extends GrantedAuthority> authorities;
        authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuth());
        return new UserDetailImpl(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getLastPasswordChange(),
                authorities,
                user.enable());
    }
}
