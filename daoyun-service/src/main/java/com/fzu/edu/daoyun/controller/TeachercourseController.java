package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.*;
import com.fzu.edu.daoyun.service.ICourseService;
import com.fzu.edu.daoyun.service.ITeachercourseService;
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
@RequestMapping("/teachercourse")
public class TeachercourseController {

    @Autowired
    private ITeachercourseService teachercourseService;
    @Autowired
    private ICourseService courseService;

    @GetMapping("/selectAll")
    @ApiOperation("查询所有班课")
    public ReturnBean selectAll(){
        return teachercourseService.selectAll();
    }

    @PostMapping("/create")
    @ApiOperation("创建班课，已实现")
    public ReturnBean createCourse(@RequestBody Banke banKe) {
        User tmp = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return courseService.createCourse(tmp, banKe.getCourse(), Integer.valueOf(banKe.getOpenYear()));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("通过主键id删除班课")
    public ReturnBean deleteClass(@PathVariable String id){
        return teachercourseService.delete(Integer.valueOf(id));
    }

}
