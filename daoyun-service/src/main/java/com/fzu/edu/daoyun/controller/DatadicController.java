package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.Datadic;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.service.IDatadicService;
import io.swagger.annotations.Api;
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
@Api("已实现")
@RestController
@RequestMapping("/dataDic")
public class DatadicController {

    @Autowired
    private IDatadicService datadicService;

    @PostMapping("/insertDic")
    @ApiOperation("增加字典条目，已实现")
    public ReturnBean insertDic(@RequestBody Datadic datadic){
        User tmp=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return datadicService.insertDatadic(datadic,tmp.getUserID());
    }
    @GetMapping("/selectDicAll")
    @ApiOperation("查询所有字典条目，已实现")
    public ReturnBean selectDicAll(){
        User tmp=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(tmp);
        return datadicService.selectAll();
    }
    @PutMapping("/updateDic")
    @ApiOperation("根据ID修改字典条目，已实现")
    public ReturnBean updateDic(@RequestBody Datadic datadic){
        User tmp=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return datadicService.updateDatadic(datadic,tmp.getUserID());
    }
    @PostMapping("/selectIdByKey/{key}")
    @ApiOperation("通过Key查询ID，已实现")
    public ReturnBean selectIdByName(@PathVariable String key){
        return datadicService.selectDatadicByKey(key);
    }

    @PutMapping("/deleteDic/{dataDicId}")
    @ApiOperation("通过ID删除字典条目，已实现")
    public ReturnBean deleteDic(@PathVariable int dataDicId){
        User tmp=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return datadicService.deleteDatadic(dataDicId,tmp.getUserID());
    }
}
