package com.cg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mybatis通用的mapper包
@MapperScan({"com.cg.gen.mapper", "com.cg.system.mapper"})
public class CgGenApplication {

    public static void main(String[] args) {
        SpringApplication.run(CgGenApplication.class, args);
    }
}
