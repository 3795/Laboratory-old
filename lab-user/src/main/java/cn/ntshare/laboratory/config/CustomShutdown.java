package cn.ntshare.laboratory.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义应用优雅停机动作
 */
@Slf4j
public class CustomShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {

    private static final int TIMEOUT = 30;

    private volatile Connector connector;

    @Override
    public void customize(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        // 暂停接收外部所有请求
        this.connector.pause();

        // 获取Connector对应的线程池
        Executor executor = this.connector.getProtocolHandler().getExecutor();
        if (executor instanceof ThreadPoolExecutor) {
            try {
                log.warn("应用准备关闭");
                ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
                // 初始化一个关闭任务位于当前待处理完毕的任务后，并拒绝新的任务提交
                threadPoolExecutor.shutdown();
                if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                    // 等待前一个已提交的任务执行完成后关闭，并且仅在最大等待时间内进行
                    log.warn("应用等待关闭时间超过 {} 秒， 准备强制关机", TIMEOUT);
                    threadPoolExecutor.shutdownNow();
                    if (!threadPoolExecutor.awaitTermination(TIMEOUT, TimeUnit.SECONDS)) {
                        log.error("应用关闭失败");
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
