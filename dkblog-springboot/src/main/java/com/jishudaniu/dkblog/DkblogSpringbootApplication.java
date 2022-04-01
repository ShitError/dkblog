package com.jishudaniu.dkblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@MapperScan("com.jishudaniu.dkblog.dao")
@SpringBootApplication
@EnableScheduling
public class DkblogSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(DkblogSpringbootApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
