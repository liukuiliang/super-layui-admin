package com.lkl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
@MapperScan(basePackages = {"com.lkl,mapper.UserMapper"})
public class LklsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LklsystemApplication.class, args);
    }

}
