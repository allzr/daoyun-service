package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.Datadicdetail;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Teachercourse;
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
@RequestMapping("/teachercourse")
public class TeachercourseController {
    @PostMapping("/insertTeaCou")
    @ApiOperation("增加教师与课程表关系")
    public ReturnBean insertTeaCou(Teachercourse teachercourse){
        return ReturnBean.success("成功");
    }
    @GetMapping("/selectTeaCou")
    @ApiOperation("查询教师与课程表关系")
    public ReturnBean selectTeaCou(Teachercourse teachercourse){
        return ReturnBean.success("成功");
    }
    @PutMapping("/updateTeaCou")
    @ApiOperation("删除教师与课程表关系")
    public ReturnBean updateTeaCou(Teachercourse teachercourse){
        return ReturnBean.success("成功");
    }
    @PutMapping("/deleteTeaCou")
    @ApiOperation("删除教师与课程表关系")
    public ReturnBean deleteTeaCou(Teachercourse teachercourse){
        return ReturnBean.success("成功");
    }
}
