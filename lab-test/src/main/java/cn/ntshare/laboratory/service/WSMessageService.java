package cn.ntshare.laboratory.service;

import cn.ntshare.laboratory.component.WebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WSMessageService {

    @Autowired
    private WebSocket webSocket;

    public Boolean sendToAllTerminal(Long userId, String message) {
        log.debug("向用户{}发送的消息：{}", userId, message);
        return webSocket.sendMessageToUser(userId, message);
    }
}
