package com.fzu.edu.daoyun.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="UserLogin对象", description="用户登录专用ENTITY")
public class UserLogin {
    @ApiModelProperty("手机号或邮箱，必填")
    private String phoneNumber;
    @ApiModelProperty("密码或验证码，必填")
    private String password;
}
