package com.fzu.edu.daoyun;


import com.fzu.edu.daoyun.controller.UserController;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.mapper.UserMapper;
import com.fzu.edu.daoyun.service.IUserService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class DaoyunApplicationTests {

//    @BeforeEach
//    public void before() throws IOException {
//        String resource = "config/mybatis-config.xml";
//        // 读取配置文件
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        // 构建sqlSessionFactory
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        // 获取sqlSession
//        SqlSession sqlSession = sqlSessionFactory.openSession(true);
//
//        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
//
//    }

    @Test
    void contextLoads(){

        User user=new User();
        user.setUsername("zhangsan");
        user.setUserType(1);
        user.setPassword("12345");
        user.setRealName("张三");


    }

}
