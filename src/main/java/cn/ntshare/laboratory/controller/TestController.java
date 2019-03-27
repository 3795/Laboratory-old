package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.model.User;
import cn.ntshare.laboratory.util.PropertiesUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * Created By Seven.wk
 * Description: 测试用的控制器
 * Created At 2018/11/10
 */
@RestController
@RequestMapping("/test")
@Slf4j
@Api(tags = "测试接口")
public class TestController {

    @GetMapping("/methodA")
    @ApiOperation("测试Swagger功能")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", defaultValue = "111", required = false, paramType = "query"),
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "Tom", required = false, paramType = "query"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功", response = User.class)
    })
    public User test(@RequestParam(value = "id", defaultValue = "111", required = false) String id,
                       @RequestParam(value = "username", defaultValue = "Tom", required = false) String username) {
        return new User(id, username);
    }
}
