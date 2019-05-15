package com.szepep.zonky.hw;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@ComponentScan(basePackageClasses = Config.class)
@PropertySource(value = "classpath:application.properties")
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newSingleThreadScheduledExecutor();
    }

}
