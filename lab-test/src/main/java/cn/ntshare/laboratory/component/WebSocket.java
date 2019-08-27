package cn.ntshare.laboratory.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{userId}")
@Slf4j
@Component
public class WebSocket {

    // 记录当前在线连接数，线程安全的计数变量
    private static volatile int onlineCount = 0;

    // 记录每个用户下多个终端的连接
//    private static Map<Long, Set<WebSocket>> userSocket = new HashMap<>();

    private static ConcurrentHashMap<Long, CopyOnWriteArraySet<WebSocket>> userSocket = new ConcurrentHashMap<>();

    // 用session对用户发送数据，获取连接特征userId
    private Session session;

    private Long userId;

    /**
     * webSocket连接时的操作
     *
     * @param userId
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("userId") Long userId, Session session) {
        this.session = session;
        this.userId = userId;
        increaseCount();
        // 检查该用户是否在其他终端登录过
        if (userSocket.containsKey(this.userId)) {
            log.info("当前用户id: {} 已有其他终端登录", this.userId);
            // 增加该用户set中的连接实例
            userSocket.get(this.userId).add(this);
        } else {
            log.info("当前用户id: {}第一个终端登录", this.userId);
            CopyOnWriteArraySet<WebSocket> userSocketSet = new CopyOnWriteArraySet<>();
            userSocketSet.add(this);
            userSocket.put(this.userId, userSocketSet);
        }
        log.info("用户{}登录的终端个数为{}", userId, userSocket.get(this.userId).size());
        log.info("当前在线用户数为：{}， 所有终端个数为：{}", userSocket.size(), onlineCount);
    }

    /**
     * webSocket关闭时的操作
     */
    @OnClose
    public void onClose() {
        // 移除当前用户终端登录的webSocket信息，如果该用户所有的终端都下线了，则删除该用户的记录
        if (userSocket.get(this.userId).size() == 0) {
            userSocket.remove(this.userId);
        } else {
            userSocket.get(this.userId).remove(this);
        }
        decreaseCount();
        log.info("用户{}登录的终端个数是为{}", this.userId, userSocket.get(this.userId).size());
        log.info("当前在线用户数为：{}, 所有终端个数为：{}", userSocket.size(), onlineCount);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自用户id: {}的信息: {}", this.userId, message);
        if (session == null) {
            log.warn("session null");
        }
    }

    /**
     * 连接发生错误时的处理
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户id: {}的连接发生错误", this.userId);
        error.printStackTrace();
    }

    /**
     * 给用户下的所有终端发送消息
     *
     * @param userId
     * @param message
     * @return
     */
    public Boolean sendMessageToUser(Long userId, String message) {
        if (userSocket.containsKey(userId)) {
            log.info("给用户id为：{}的所有终端发送消息：{}", userId, message);
            for (WebSocket ws : userSocket.get(userId)) {
                log.info("sessionId为：{}", ws.session.getId());
                try {
                    ws.session.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    log.error("用户id：{}发送消息失败", userId);
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }
        log.warn("用户{}不存在", userId);
        return false;
    }

    /**
     * 增加计数
     */
    private synchronized void increaseCount() {
        onlineCount++;
    }

    /**
     * 减少计数
     */
    private synchronized void decreaseCount() {
        onlineCount --;
    }
}
