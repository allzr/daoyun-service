package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.Datadic;
import com.fzu.edu.daoyun.entity.ReturnBean;
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
@RequestMapping("/dataDic")
public class DatadicController {
    @PostMapping("/insertDic")
    @ApiOperation("增加字典条目")
    public ReturnBean insertDic(Datadic datadic){
        return ReturnBean.success("成功");
    }
    @GetMapping("/selectDic")
    @ApiOperation("查询字典条目")
    public ReturnBean selectDic(Datadic datadic){
        return ReturnBean.success("成功");
    }
    @PutMapping("/updateDic")
    @ApiOperation("修改字典条目")
    public ReturnBean updateDic(Datadic datadic){
        return ReturnBean.success("成功");
    }
    @PutMapping("/deleteDic")
    @ApiOperation("增加字典条目")
    public ReturnBean deleteDic(Datadic datadic){
        return ReturnBean.success("成功");
    }
}
