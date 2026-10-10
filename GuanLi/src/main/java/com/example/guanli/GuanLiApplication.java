package com.example.guanli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan//启用Servlet组件扫描
@SpringBootApplication
public class GuanLiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuanLiApplication.class, args);
    }

}
