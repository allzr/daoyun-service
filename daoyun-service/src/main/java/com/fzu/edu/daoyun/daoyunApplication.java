package com.fzu.edu.daoyun;

import com.fzu.edu.daoyun.controller.UserController;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.IUserService;
import com.fzu.edu.daoyun.service.impl.UserServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fzu.edu.daoyun.mapper")
public class daoyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(daoyunApplication.class,args);

    }
}
