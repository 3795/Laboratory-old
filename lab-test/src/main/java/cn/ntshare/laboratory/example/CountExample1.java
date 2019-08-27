package cn.ntshare.laboratory.example;

import cn.ntshare.laboratory.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created By Q.Hao
 * Description: 使用最简单的计数方法，是线程不安全的
 * Created At 2019/8/18
 */
@Slf4j
@NoThreadSafe
public class CountExample1 {

    // 请求总数
    private static int clientTotal = 5000;
    // 线程并发数
    private static int threadTotal = 200;

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0; i<clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch(Exception e) {
                    log.error(e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count = {}", count);
    }

    private static void add() {
        count ++;
    }
}
