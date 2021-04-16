package com.fzu.edu.daoyun.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="CodeDeatil对象", description="接收验证码使用")
public class CodeDetail {
    @ApiModelProperty("验证码，必填")
    String code;
    @ApiModelProperty("过期时间，不填")
    LocalDateTime deadtime;



}
