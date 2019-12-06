package com.core.springbootcore;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.core.springbootcore.*.mapper")
public class SpringbootCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCoreApplication.class, args);
    }

}
