package cn.ntshare.laboratory.realm;

import cn.ntshare.laboratory.entity.Role;
import cn.ntshare.laboratory.entity.User;
import cn.ntshare.laboratory.exception.ServerException;
import cn.ntshare.laboratory.service.PermissionService;
import cn.ntshare.laboratory.service.RoleService;
import cn.ntshare.laboratory.service.UserService;
import cn.ntshare.laboratory.token.JWTToken;
import cn.ntshare.laboratory.token.JWTUser;
import cn.ntshare.laboratory.util.JWTUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JWTRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行一次身份授权");
        JWTUser jwtUser = (JWTUser) principalCollection.getPrimaryPrincipal();
        String username = JWTUtil.getUsername(jwtUser.getToken());
        User user = userService.findByAccount(username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        Set<String> roleSet = new HashSet<>();
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleSet.add(role.getRole());
            roleIds.add(role.getId());
        }
        authorizationInfo.setRoles(roleSet);

        List<String> permissionList = permissionService.findByRoleId(roleIds);
        authorizationInfo.setStringPermissions(new HashSet<>(permissionList));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) {
        System.out.println("执行一次身份认证");
        String token = (String) authenticationToken.getCredentials();
        String username = (String) authenticationToken.getPrincipal();
        if (username == null) {
            throw new ServerException("token invalid");
        }
        User user = userService.findByAccount(username);
        if (user == null) {
            throw new ServerException("User didn't existed!");
        }

        if (!JWTUtil.verify(token, username, user.getPassword())) {
            throw new ServerException("Username or password error");
        }

        JWTUser jwtUser = new JWTUser();
        try {
            BeanUtils.copyProperties(jwtUser, user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        jwtUser.setToken(token);

        return new SimpleAuthenticationInfo(jwtUser, token, getName());
    }
}
