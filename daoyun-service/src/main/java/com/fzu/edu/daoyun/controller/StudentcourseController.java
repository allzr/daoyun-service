package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Studentteacou;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.IStudentteacouService;
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
@RestController
@RequestMapping("studentcourse")
public class StudentcourseController {
    @Autowired
    private IStudentteacouService studentteacouService;
    @PostMapping("/joinClass/{classID}")
    @ApiOperation("加入班课")
    public ReturnBean insertStuCou(@PathVariable String classID){
        return studentteacouService.joinClass(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserID(), classID);
    }
    @GetMapping("/selectByStudent")
    @ApiOperation("查询学生加入的班课")
    public ReturnBean selectByStudent(){
        return studentteacouService.selectByUserId(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserID());
    }
    @GetMapping("/selectByClass/{classID}")
    @ApiOperation("查询班课中的学生")
    public ReturnBean selectByClass(@PathVariable String classID){
        return studentteacouService.selectByClassId(classID);
    }
    @PutMapping("/deleteStuCou")
    @ApiOperation("删除学生与课程表关系")
    public ReturnBean deleteStuCou(Studentteacou studentteacou){
        return ReturnBean.success("成功");
    }
}
