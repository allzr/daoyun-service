package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Teachercourse;
import com.fzu.edu.daoyun.entity.Url;
import io.swagger.annotations.Api;
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
@Api("已实现")
@RestController
@RequestMapping("/url")
public class UrlController {
    @PostMapping("/insertUrl/user/{userID}")
    @ApiOperation("添加URL，已实现")
    public ReturnBean insertUrl(@RequestBody Url url,@PathVariable  int userID)
    {
        return ReturnBean.success("成功");
    }
    @GetMapping("/selectUrl/{urlId}")
    @ApiOperation("查询URL，已实现")
    public ReturnBean selectUrl(@PathVariable String id){
        return ReturnBean.success("成功");
    }
    @PutMapping("/updateUrl/user/{id}")
    @ApiOperation("修改URL，已实现")
    public ReturnBean updateUrl(@RequestBody Url url, @PathVariable String id){
        return ReturnBean.success("成功");
    }
}
