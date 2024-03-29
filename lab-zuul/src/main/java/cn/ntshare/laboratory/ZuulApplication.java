package cn.ntshare.laboratory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/5/19
 */
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
@Slf4j
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
