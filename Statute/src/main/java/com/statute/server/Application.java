package com.statute.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
/**
* @description: 主启动类
* @author: liwenhui
* @create: 2026.04.07 14:09:31
*/
@MapperScan("com.statute.server.dao")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
