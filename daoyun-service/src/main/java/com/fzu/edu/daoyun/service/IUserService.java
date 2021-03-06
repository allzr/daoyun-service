package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.entity.UserLogin;
import com.fzu.edu.daoyun.entity.UserRegiser;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface IUserService extends IService<User> {

    ReturnBean loginByPassword(UserLogin userLogin);

    ReturnBean register(UserRegiser userRegiser);

    User getUserByUsername(String username);

    User getUserByPhoneNumber(String phoneNumber);

    ReturnBean getCode(String phoneNumber);

    User getUserByEmail(String Email);

    ReturnBean deleteUser(String phoneNumber);

    ReturnBean sendCode(String phoneNumber, String code);

    ReturnBean loginByCode(UserLogin userLogin);
}
