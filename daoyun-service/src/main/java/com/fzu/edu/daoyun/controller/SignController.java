package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.SignDetail;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.ISignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getSignInfo/{teaCouID}")
    @ApiOperation("老师获取当前班课发起签到信息，已实现")
    public ReturnBean getSignCreateTime(@PathVariable String teaCouID){return signService.getSignCreateTime(Integer.valueOf(teaCouID));}

    @PostMapping("/getStudentSignInfo")
    @ApiOperation("老师获取当前发起签到时间下的学生签到信息，已实现")
    public ReturnBean getStudentSignInfo(@RequestBody  SignDetail signDetail){
        return signService.getSignInfo(signDetail);
    }

    @GetMapping("/getStudentSignInfo/{signID}")
    @ApiOperation("老师根据signID获取学生签到信息，已实现")
    public ReturnBean getStudentSignInfoBySignID(@PathVariable String signID){

        return signService.getSignInfoBySignID(Integer.valueOf(signID));
    }
}
