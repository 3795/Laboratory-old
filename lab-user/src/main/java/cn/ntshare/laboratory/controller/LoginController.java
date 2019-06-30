package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.entity.User;
import cn.ntshare.laboratory.enums.ServerResponseEnum;
import cn.ntshare.laboratory.util.JWTUtil;
import cn.ntshare.laboratory.vo.ServerResponseVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

// https://www.jianshu.com/p/0a5d3d07a151

@RestController
@RequestMapping("")
public class LoginController {

    @PostMapping("/login")
    public ServerResponseVO login(@RequestParam(value = "account") String account,
                                  @RequestParam(value = "password") String password) {
//        Subject userSubject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
//        try {
//            // 登录验证
//            userSubject.login(token);
//            // 封装返回信息
//            User user = (User) userSubject.getPrincipal();
//            return ServerResponseVO.success(JWTUtil.sign(user.getAccount(), user.getPassword()));
//        } catch (UnknownAccountException e) {
//            return ServerResponseVO.error(ServerResponseEnum.ACCOUNT_NOT_EXIST);
//        } catch (DisabledAccountException e) {
//            return ServerResponseVO.error(ServerResponseEnum.ACCOUNT_IS_DISABLED);
//        } catch (IncorrectCredentialsException e) {
//            return ServerResponseVO.error(ServerResponseEnum.INCORRECT_CREDENTIALS);
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return ServerResponseVO.error(ServerResponseEnum.ERROR);
//        }
        if ("root".equals(account) && "root".equals(password)) {
            return ServerResponseVO.success(JWTUtil.sign(account, password));
        }
        return null;
    }

    @GetMapping("/login")
    public ServerResponseVO login() {
        return ServerResponseVO.error(ServerResponseEnum.NOT_LOGIN_IN);
    }

    @GetMapping("/auth")
    public String auth() {
        return "已成功登录";
    }

    @GetMapping("/role")
    @RequiresRoles("admin")
    public String role() {
        return "测试Admin角色";
    }

    @GetMapping("/permission")
    @RequiresPermissions(value = {"add", "update"}, logical = Logical.AND)
    public String permission() {
        return "测试Add和Update权限";
    }
}
