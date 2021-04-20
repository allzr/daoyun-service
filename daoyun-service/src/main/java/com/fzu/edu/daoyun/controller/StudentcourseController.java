package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Studentteacou;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@RestController
@RequestMapping("studentcourse")
public class StudentcourseController {
    @PostMapping("/insertStuCou")
    @ApiOperation("增加学生与课程关系")
    public ReturnBean insertStuCou(Studentteacou studentteacou){
        return ReturnBean.success("成功");
    }
    @GetMapping("/selectStuCou")
    @ApiOperation("查询学生与课程关系")
    public ReturnBean selectStuCou(Studentteacou studentteacou){
        return ReturnBean.success("成功");
    }
    @PutMapping("/updateStuCou")
    @ApiOperation("删除学生与课程关系")
    public ReturnBean updateStuCou(Studentteacou studentteacou){
        return ReturnBean.success("成功");
    }
    @PutMapping("/deleteStuCou")
    @ApiOperation("删除学生与课程表关系")
    public ReturnBean deleteStuCou(Studentteacou studentteacou){
        return ReturnBean.success("成功");
    }
}
