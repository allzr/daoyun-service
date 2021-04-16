package com.fzu.edu.daoyun.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="UserRegister对象", description="用户注册专用ENTITY")
public class UserRegiser {
    @ApiModelProperty("手机号或邮箱，必填")
    private String phoneNumber;
    @ApiModelProperty("确认密码")
    private String rePassword;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("验证码")
    private String code;
    @ApiModelProperty("用户类型 1为学生 2为老师")
    private int userType;

}
