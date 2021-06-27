package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.Logintime;
import com.fzu.edu.daoyun.entity.User;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface ILogintimeService extends IService<Logintime> {
    void insertLoginTime(User user, LocalDateTime localDateTime);
}
