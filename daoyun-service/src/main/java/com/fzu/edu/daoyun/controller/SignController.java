package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Sign;
import com.fzu.edu.daoyun.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/sign")
public class SignController {
    @PostMapping("/insertSign")
    @ApiOperation("添加签到信息")
    public ReturnBean insertSign(Sign sign){
        return ReturnBean.success("成功");
    }
    @PostMapping("/updateSign")
    @ApiOperation("修改签到信息")
    public ReturnBean updateSign(Sign sign){
        return ReturnBean.success("成功");
    }
}
