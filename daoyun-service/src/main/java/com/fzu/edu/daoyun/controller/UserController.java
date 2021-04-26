package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.config.security.JwtTokenUtils;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.entity.UserLogin;
import com.fzu.edu.daoyun.entity.UserRegiser;
import com.fzu.edu.daoyun.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Reference;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
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
@Api("已实现")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Resource
    public HttpServletRequest request;

    @PostMapping("/loginByPassword")
    @ApiOperation(value = "密码登录，已实现")
    public ReturnBean loginByPassword(@RequestBody  UserLogin userLogin){
        return userService.loginByPassword(userLogin);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "仅修改密码，已实现")
    public ReturnBean updatePassword(@RequestBody  UserLogin userLogin){
        return userService.updatePassword(userLogin);
    }

    @PostMapping("/loginByCode")
    @ApiOperation(value = "验证码登录，已实现")
    public ReturnBean loginByCode(@RequestBody  UserLogin userLogin){
        return userService.loginByCode(userLogin);
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册，已实现")
    public ReturnBean register(@RequestBody UserRegiser userRegiser){
        return userService.register(userRegiser);
    }

    @PostMapping("/code")
    @ApiOperation(value = "获取验证码，已实现")
    public  ReturnBean getCode(@RequestBody String phoneNumber){
        System.out.println(phoneNumber);
        return userService.getCode(phoneNumber);
    }

    @PostMapping("/returnCode")
    @ApiOperation(value = "获取验证码并返回，已实现")
    public  ReturnBean getCodeAndReturn(@RequestBody String phoneNumber){
        return userService.getCodeAndReturn(phoneNumber);
    }

    @PutMapping("/insert")
    @ApiOperation(value = "添加用户，已实现")
    public ReturnBean insert(@RequestBody  User user){
        return ReturnBean.success("添加成功");
    }

    @GetMapping("/logout")
    @ApiOperation(value = "注销，已实现")
    public ReturnBean logout(){
        return ReturnBean.success("注销成功！");
    }

    @GetMapping("/info/{phoneNumber}")
    @ApiOperation(value = "根据手机号，获取当前用户信息，已实现")
    public ReturnBean getUserInfo(@PathVariable String phoneNumber){
        User user = userService.getUserByPhoneNumber(phoneNumber);
        if(null == user)
            return ReturnBean.error("查无此人");
        user.setPassword(null);
        return ReturnBean.success("查询成功",user);
    }

    @GetMapping("/delete/{phoneNumber}")
    @ApiOperation(value = "通过手机号码删除用户，已实现")
    public ReturnBean delete(@PathVariable String phoneNumber){
        return userService.deleteUser(phoneNumber);
    }

    @PostMapping("/changePassword")
    @ApiOperation(value="修改密码,需要token，已实现")
    public ReturnBean changePassword(@RequestBody UserLogin userLogin){
        //request.getHeader("Athor");
        return userService.getNewPassword(userLogin);
    }


}
