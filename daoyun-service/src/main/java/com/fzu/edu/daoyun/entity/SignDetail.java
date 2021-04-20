package com.fzu.edu.daoyun.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="SignDetail对象", description="用户签到专用ENTITY")
public class SignDetail {
    int teaCouID;
    int userID;
    String LocationX;
    String LocationY;
    int time;
    LocalDateTime createTime;
}
