package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Teachercourse;
import com.fzu.edu.daoyun.entity.Url;
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
@RequestMapping("/url")
public class UrlController {
    @PostMapping("/insertUrl")
    @ApiOperation("添加URL")
    public ReturnBean insertUrl(Url url)
    {
        return ReturnBean.success("成功");
    }
    @GetMapping("/selectUrl")
    @ApiOperation("查询URL")
    public ReturnBean selectUrl(Url url){
        return ReturnBean.success("成功");
    }
    @PutMapping("/updateUrl")
    @ApiOperation("修改URL")
    public ReturnBean updateUrl(Url url){
        return ReturnBean.success("成功");
    }
    @PutMapping("/deleteUrl/{id}")
    @ApiOperation("删除URL")
    public ReturnBean deleteUrl(@PathVariable String id) { return ReturnBean.success("成功");
    }
}
