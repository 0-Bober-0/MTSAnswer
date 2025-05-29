package com.sasha.mts;

import com.sasha.mts.config.HeaderProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(HeaderProperties.class)
public class MTSApplication {
    public static void main(String[] args) {
        SpringApplication.run(MTSApplication.class, args);
    }
}
