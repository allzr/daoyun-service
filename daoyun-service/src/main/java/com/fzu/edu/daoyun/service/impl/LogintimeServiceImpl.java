package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.Logintime;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.mapper.LogintimeMapper;
import com.fzu.edu.daoyun.service.ILogintimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Service
public class LogintimeServiceImpl extends ServiceImpl<LogintimeMapper, Logintime> implements ILogintimeService {

    @Autowired
    private LogintimeMapper logintimeMapper;

    @Override
    public void insertLoginTime(User user, LocalDateTime localDateTime, int loginType) {
        Logintime logintime=new Logintime();
        logintime.setUserID(user.getUserID());
        logintime.setLoginType(loginType);
        logintime.setLoginTime(localDateTime);
        
    }
}
