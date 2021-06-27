package com.fzu.edu.daoyun.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fzu.edu.daoyun.entity.Course;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.mapper.CourseMapper;
import com.fzu.edu.daoyun.service.ITeachercourseService;
import com.fzu.edu.daoyun.service.impl.CourseServiceImpl;
import com.fzu.edu.daoyun.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.fzu.edu.daoyun.entity.Banke;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ITeachercourseService teachercourseService;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CourseMapper courseMapper;

    @PostMapping("/create")
    @ApiOperation("创建班课，已实现")
    public ReturnBean createCourse(@RequestBody Banke banKe) {

        //User principal1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User tmp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return courseService.createCourse(tmp, banKe.getCourse(), Integer.valueOf(banKe.getOpenYear()));
    }

    @PostMapping("/insert/{phoneNumber}")
    @ApiOperation("管理员添加班课，已实现")
    public ReturnBean createCourse(@RequestBody Banke banKe,@PathVariable String phoneNumber) {

        //User principal1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User tmp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return courseService.insertCourse(tmp, banKe.getCourse(), Integer.valueOf(banKe.getOpenYear()),phoneNumber);
    }

    @PutMapping("/delete/{id}")
    @ApiOperation("删除班课，已实现")
    public ReturnBean deleteCourse(@PathVariable String id) {
        return courseService.deleteCourse(id);
    }

    @GetMapping("selectById/{id}")
    @ApiOperation("根据用户ID查询班课")
    public ReturnBean selectCourseById(@PathVariable String id) {
        return courseService.selectCourseById(id);
    }


    @PutMapping("/update")
    @ApiOperation("修改班课")
    public ReturnBean updateCourse(Course course) {
        return ReturnBean.success("成功");
    }

    @GetMapping("selectByClassID/{classID}")
    @ApiOperation("通过班课号查询班课")
    public ReturnBean selectCourse(@PathVariable String classID) {
        return teachercourseService.getTeaCouInfo(classID);
    }

    @GetMapping("getCourses")
    @ApiOperation("通过班课号查询班课")
    public ReturnBean getCourses() {
        List<Course> courses = courseMapper.selectList(new QueryWrapper<>());
        List<String> courseName = new ArrayList<>();
        for (Course cours : courses) {
            courseName.add(cours.getName().split("-")[0]);
        }
        return ReturnBean.success("ok",courseName);
    }
}


