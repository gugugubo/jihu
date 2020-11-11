package com.gdut.jiyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.gdut.jiyi.mapper","com.gdut.jiyi.mapperMore"})
public class JiyiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiyiApplication.class, args);
    }

}
