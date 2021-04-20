package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Sign;
import com.fzu.edu.daoyun.entity.SignDetail;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.ISignService;
import com.fzu.edu.daoyun.util.SignSave;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api("已实现")
@RestController
@RequestMapping("/sign")
public class SignController {
    @Autowired
    private ISignService signService;

    @PostMapping("/teaBegin")
    @ApiOperation("老师发布一键签到，已实现")
    public ReturnBean teaBeginSign(@RequestBody  SignDetail signDetail){
        return signService.beginSign(signDetail);
    }

    @PostMapping("/teaBeginByTime")
    @ApiOperation("老师发布限时签到，已实现")
    public ReturnBean teaBeginSignByTime(@RequestBody  SignDetail signDetail){
        return signService.beginSignByTime(signDetail);
    }

    @PostMapping("/finishSign")
    @ApiOperation("老师结束当前签到，已实现")
    public ReturnBean finishSign(@RequestBody  SignDetail signDetail){
        return signService.finishSign(signDetail);
    }

    @PostMapping("/stuSign")
    @ApiOperation("学生签到，已实现")
    public ReturnBean stuSign(@RequestBody  SignDetail signDetail){return signService.studentSign(signDetail);}

    @PostMapping("/getSignInfo")
    @ApiOperation("老师获取自己发起签到信息，已实现")
    public ReturnBean getSignCreateTime(@RequestBody  SignDetail signDetail){return signService.getSignCreateTime(signDetail.getTeaCouID());}

    @PostMapping("/getStudentSignInfo")
    @ApiOperation("老师获取当前发起签到时间下的学生签到信息，已实现")
    public ReturnBean getStudentSignInfo(@RequestBody  SignDetail signDetail){
        return signService.getSignInfo(signDetail);
    }
}
