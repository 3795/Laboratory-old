package cn.ntshare.laboratory.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomShutdownConfig {

    @Bean
    public CustomShutdown customShutdown() {
        return new CustomShutdown();
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory(final CustomShutdown customShutdown) {
        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        tomcatServletWebServerFactory.addConnectorCustomizers(customShutdown);
        return tomcatServletWebServerFactory;
    }
}
