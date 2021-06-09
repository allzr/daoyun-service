package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.Course;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.impl.CourseServiceImpl;
import com.fzu.edu.daoyun.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.fzu.edu.daoyun.entity.Banke;
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
    public ReturnBean createCourse(@RequestBody Banke banKe)
    {

        //User principal1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User tmp=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return courseService.createCourse(tmp, banKe.getCourse(), Integer.valueOf(banKe.getOpenYear()));
    }
    @PutMapping("/delete/{id}")
    @ApiOperation("删除班课，已实现")
    public ReturnBean deleteCourse(@PathVariable String id){
        return courseService.deleteCourse(id);
    }

    @GetMapping("selectById/{id}")
    @ApiOperation("根据用户ID查询班课")
    public ReturnBean selectCourseById(@PathVariable String id){
        return courseService.selectCourseById(id);
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


