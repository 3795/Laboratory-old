package cn.ntshare.laboratory.realm;

import cn.ntshare.laboratory.entity.Role;
import cn.ntshare.laboratory.entity.User;
import cn.ntshare.laboratory.service.PermissionService;
import cn.ntshare.laboratory.service.RoleService;
import cn.ntshare.laboratory.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 负责认证用户身份和对用户进行授权
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    // 用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了一次授权");
        User user = (User) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<Role> roleList = roleService.findRoleByUserId(user.getId());
        Set<String> roleSet = new HashSet<>();
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleSet.add(role.getRole());
            roleIds.add(role.getId());
        }
        // 放入角色信息
        authorizationInfo.setRoles(roleSet);
        // 放入权限信息
        List<String> permissionList = permissionService.findByRoleId(roleIds);
        authorizationInfo.setStringPermissions(new HashSet<>(permissionList));

        return authorizationInfo;
    }

    // 用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
        System.out.println("执行了身份认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        User user = userService.findByAccount(token.getUsername());
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * 清除当前授权缓存
     * @param principalCollection
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        super.clearCachedAuthorizationInfo(principalCollection);
    }

    /**
     * 清除当前用户身份认证缓存
     * @param principalCollection
     */
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principalCollection) {
        super.clearCachedAuthenticationInfo(principalCollection);
    }

    @Override
    public void clearCache(PrincipalCollection principalCollection) {
        super.clearCache(principalCollection);
    }
}
