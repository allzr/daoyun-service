package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.Datadic;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.service.IDatadicService;
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
@RequestMapping("/dataDic")
public class DatadicController {

    private IDatadicService datadicService;

    @PostMapping("/insertDic/user/{id}")
    @ApiOperation("增加字典条目，已实现")
    public ReturnBean insertDic(@RequestBody Datadic datadic, @PathVariable int id){
        return datadicService.insertDatadic(datadic,id);
    }
    @GetMapping("/selectDicAll")
    @ApiOperation("查询所有字典条目，已实现")
    public ReturnBean selectDic(){
        return datadicService.selectAll();
    }
    @PutMapping("/updateDic/user/{id}")
    @ApiOperation("根据ID修改字典条目，已实现")
    public ReturnBean updateDic(@RequestBody Datadic datadic, @PathVariable int id){
        return datadicService.updateDatadic(datadic,id);
    }
    @PostMapping("/selectIdByKey/{key}")
    @ApiOperation("通过Key查询ID，已实现")
    public ReturnBean selectIdByName(@PathVariable String key){
        return ReturnBean.success("查询成功",datadicService.getDatadicByKey(key).getDataDicID());
    }

    @PutMapping("/deleteDic/{dataDicId}/user/{userID}")
    @ApiOperation("通过ID删除字典条目，已实现")
    public ReturnBean deleteDic(@PathVariable int dataDicId, @PathVariable int userID){
        return deleteDic(dataDicId,userID);
    }
}
