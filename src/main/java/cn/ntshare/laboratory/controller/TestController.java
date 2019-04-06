package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.model.User;
import cn.ntshare.laboratory.util.FtpUtil;
import cn.ntshare.laboratory.util.PropertiesUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @PostMapping("/upload/image")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        // 现将文件暂存到该路径下
        String sysPath = "./src/main/resources/static/images/";
        String fileName = file.getOriginalFilename();
        File image = new File(sysPath + fileName);
        FileUtils.copyInputStreamToFile(file.getInputStream(), image);
        boolean result = FtpUtil.uploadImg(image);

        // 删除项目中暂存的图片文件
        image.delete();

        if (result) {
            return "文件上传成功";
        }
        return "文件上传失败";
    }
}
