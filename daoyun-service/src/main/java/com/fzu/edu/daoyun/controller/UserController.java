package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.entity.UserLogin;
import com.fzu.edu.daoyun.entity.UserRegiser;
import com.fzu.edu.daoyun.service.IUserService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @PostMapping("/loginByPassword")
    @ApiOperation(value = "密码登录")
    public ReturnBean loginByPassword(@RequestBody  UserLogin userLogin){
        return userService.loginByPassword(userLogin);
    }

    @PostMapping("/loginByCode")
    @ApiOperation(value = "验证码登录")
    public ReturnBean loginByCode(@RequestBody  UserLogin userLogin){
        return userService.loginByCode(userLogin);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public ReturnBean register(@RequestBody UserRegiser userRegiser){
        return userService.register(userRegiser);
    }

    @PostMapping("/code")
    @ApiOperation(value = "获取验证码")
    public  ReturnBean getCode(@RequestBody String phoneNumber){
        return userService.getCode(phoneNumber);
    }

    @PutMapping("/insert")
    @ApiOperation(value = "添加用户")
    public ReturnBean insert(@RequestBody  User user){
        return ReturnBean.success("添加成功");
    }

    @GetMapping("/logout")
    @ApiOperation(value = "注销")
    public ReturnBean logout(){
        return ReturnBean.success("注销成功！");
    }

    @GetMapping("/info/{phoneNumber}")
    @ApiOperation(value = "根据手机号，获取当前用户信息")
    public ReturnBean getUserInfo(@PathVariable String phoneNumber){
        User user = userService.getUserByPhoneNumber(phoneNumber);
        if(null == user)
            return ReturnBean.error("查无此人");
        user.setPassword(null);
        return ReturnBean.success("查询成功",user);
    }

    @GetMapping("/delete/{phoneNumber}")
    @ApiOperation(value = "通过手机号码删除用户")
    public ReturnBean delete(@PathVariable String phoneNumber){
        return userService.deleteUser(phoneNumber);
    }
}
