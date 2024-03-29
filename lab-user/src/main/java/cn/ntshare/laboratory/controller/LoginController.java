package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.enums.ServerResponseEnum;
import cn.ntshare.laboratory.service.UserService;
import cn.ntshare.laboratory.util.JWTUtil;
import cn.ntshare.laboratory.vo.ServerResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// https://www.jianshu.com/p/0a5d3d07a151

@RestController
@RequestMapping("")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ServerResponseVO login(@RequestParam(value = "account") String account,
                                  @RequestParam(value = "password") String password) {
        if (userService.login(account, password)) {
            return ServerResponseVO.success(JWTUtil.sign(account, password));
        } else {
            return ServerResponseVO.error(ServerResponseEnum.INCORRECT_CREDENTIALS);
        }
    }

    @GetMapping("/login")
    public ServerResponseVO login() {
        return ServerResponseVO.error(ServerResponseEnum.NOT_LOGIN_IN);
    }

    @RequiresAuthentication
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

    @GetMapping("/test")
    public String test() throws InterruptedException {
        log.warn("开始处理业务");
        Thread.sleep(10000);
        log.warn("业务处理完成");
        return "业务完成";
    }
}
