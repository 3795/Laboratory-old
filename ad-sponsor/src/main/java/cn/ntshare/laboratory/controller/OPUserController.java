package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.service.IUserService;
import cn.ntshare.laboratory.vo.CreateUserRequest;
import cn.ntshare.laboratory.vo.CreateUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Slf4j
public class OPUserController {

    @Autowired
    private IUserService userService;

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}
