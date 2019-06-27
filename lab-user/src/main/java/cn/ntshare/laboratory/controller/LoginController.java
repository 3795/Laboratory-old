package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.entity.User;
import cn.ntshare.laboratory.enums.ServerResponseEnum;
import cn.ntshare.laboratory.vo.ServerResponseVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class LoginController {

    @PostMapping("/login")
    public ServerResponseVO login(@RequestParam(value = "account") String account,
                                  @RequestParam(value = "password") String password) {
        Subject userSubject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            // 登录验证
            userSubject.login(token);
            // 封装返回信息
            User user = (User) userSubject.getPrincipal();
            return ServerResponseVO.success(user);
        } catch (UnknownAccountException e) {
            return ServerResponseVO.error(ServerResponseEnum.ACCOUNT_NOT_EXIST);
        } catch (DisabledAccountException e) {
            return ServerResponseVO.error(ServerResponseEnum.ACCOUNT_IS_DISABLED);
        } catch (IncorrectCredentialsException e) {
            return ServerResponseVO.error(ServerResponseEnum.INCORRECT_CREDENTIALS);
        } catch (Throwable e) {
            return ServerResponseVO.error(ServerResponseEnum.ERROR);
        }
    }

    @GetMapping("/login")
    public ServerResponseVO login() {
        return ServerResponseVO.error(ServerResponseEnum.NOT_LOGIN_IN);
    }

    @GetMapping("/unauthorized")
    public ServerResponseVO unauthorized() {
        return ServerResponseVO.error(ServerResponseEnum.UNAUTHORIZED);
    }

    @GetMapping("/test")
    public String test() {
        return "测试";
    }

    @GetMapping("/test2")
    @RequiresRoles("admin")
    public String test2() {
        return "测试权限";
    }
}
