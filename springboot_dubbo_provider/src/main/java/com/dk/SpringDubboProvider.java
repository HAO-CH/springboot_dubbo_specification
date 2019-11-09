package com.dk;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan("com.dk.mapper")
public class SpringDubboProvider {

    public static void main(String[] args) {
        SpringApplication.run(SpringDubboProvider.class, args);
    }

}
