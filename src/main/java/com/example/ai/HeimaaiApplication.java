package com.example.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ai.mapper")
public class HeimaaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeimaaiApplication.class, args);
    }

} 