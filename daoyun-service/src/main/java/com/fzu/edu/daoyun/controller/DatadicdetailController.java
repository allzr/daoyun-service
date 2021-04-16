package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.Datadic;
import com.fzu.edu.daoyun.entity.Datadicdetail;
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
@RequestMapping("/datadicdetail")
public class DatadicdetailController {
    @PostMapping("insertDicDetail")
    @ApiOperation("增加字典细节条目")
    public ReturnBean insertDicDetial(Datadicdetail datadicdetail){
        return ReturnBean.success("成功");
    }
    @GetMapping("/selectDicDetail")
    @ApiOperation("查询字典细节条目")
    public ReturnBean selectDicDetial(Datadicdetail datadicdetail){
        return ReturnBean.success("成功");
    }
    @PutMapping("/updateDicDetail")
    @ApiOperation("删除字典细节条目")
    public ReturnBean updateDicDetial(Datadicdetail datadicdetail){
        return ReturnBean.success("成功");
    }
    @PutMapping("/deleteDicDetail")
    @ApiOperation("删除字典细节条目")
    public ReturnBean deleteDicDetial(Datadicdetail datadicdetail){
        return ReturnBean.success("成功");
    }
}
