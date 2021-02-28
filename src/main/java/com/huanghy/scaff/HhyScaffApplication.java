package com.huanghy.scaff;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huanghy.scaff.mapper")
public class HhyScaffApplication {

    public static void main(String[] args) {
        SpringApplication.run(HhyScaffApplication.class, args);
    }

}
