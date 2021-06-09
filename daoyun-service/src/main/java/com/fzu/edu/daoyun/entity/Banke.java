package com.fzu.edu.daoyun.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="BanKe对象", description="创建班课专用ENTITY")
public class Banke {
    String openYear;
    Course course;
}
