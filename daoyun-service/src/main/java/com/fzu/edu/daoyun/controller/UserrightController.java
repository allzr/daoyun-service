package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Url;
import com.fzu.edu.daoyun.entity.Userright;
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
@RequestMapping("/userright")
public class UserrightController {
    @PostMapping("/insertUserRight")
    @ApiOperation("添加用户权限")
    public ReturnBean insertUserRight(Userright userright)
    {
        return ReturnBean.success("成功");
    }
    @GetMapping("/selectUserRight")
    @ApiOperation("查询用户权限")
    public ReturnBean selectUserRight(Userright userright){
        return ReturnBean.success("成功");
    }
    @PutMapping("/updateUserRight")
    @ApiOperation("修改用户权限")
    public ReturnBean updateUserRight(Userright userright){
        return ReturnBean.success("成功");
    }
}
