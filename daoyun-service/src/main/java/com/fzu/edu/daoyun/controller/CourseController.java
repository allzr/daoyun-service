package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.Course;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.service.impl.CourseServiceImpl;
import com.fzu.edu.daoyun.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/course")
public class CourseController {


    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create")
    @ApiOperation("创建班课，已实现")
    public ReturnBean createCourse(Principal principal, Course course ,@RequestParam(value = "开学年份") int year)
    {
        return courseService.createCourse(userService.getUserByUsername(principal.getName()),course,year);
    }
    @PutMapping("/delete/{id}")
    @ApiOperation("删除班课，已实现")
    public ReturnBean deleteCourse(@PathVariable String id){
        return courseService.deleteCourse(id);
    }
    @PutMapping("/update")
    @ApiOperation("修改班课")
    public ReturnBean updateCourse(Course course){
        return ReturnBean.success("成功");
    }
    @GetMapping("select")
    @ApiOperation("查询班课")
    public ReturnBean selectCourse(Course course){
        return ReturnBean.success("成功");
    }
}
