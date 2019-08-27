package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.service.WSMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @Autowired
    private WSMessageService wsMessageService;

    @PostMapping
    public String sendMessage(@RequestParam("userId") Long userId,
                              @RequestParam("message") String message) {
        log.debug("将向用户{}发送消息：{}", userId, message);
        if (wsMessageService.sendToAllTerminal(userId, message)) {
            return "发送成功";
        } else {
            return "发送失败";
        }
    }
}
