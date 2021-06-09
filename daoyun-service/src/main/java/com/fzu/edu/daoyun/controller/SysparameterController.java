package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.*;
import com.fzu.edu.daoyun.service.ISysparameterService;
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
@Api("")
@RestController
@RequestMapping("/Sysparameter")
public class SysparameterController {

    @Autowired
    private ISysparameterService sysparameterService;

    @PostMapping("/insertSysParameter")
    @ApiOperation("添加系统参数，已实现")
    public ReturnBean insertSysparameter(@RequestBody Sysparameter sysparameter)
    {
        User tmp=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return sysparameterService.insertSysParameter(sysparameter,tmp.getUserID());
    }

    @GetMapping("/selectAll")
    @ApiOperation("查询系统参数，已实现")
    public ReturnBean selectAll(){
        return sysparameterService.selectAll();
    }

    @GetMapping("/selectByKey/{Key}")
    @ApiOperation("通过Key查询系统参数，已实现")
    public ReturnBean selectByKey(@PathVariable String Key){
        return sysparameterService.selectByKey(Key);
    }

    @DeleteMapping("/deleteSysParameterById/{SysParameterId}")
    @ApiOperation("删除系统参数，已实现")
    public ReturnBean deleteSysParameterById(@PathVariable String SysParameterId){
        return sysparameterService.deleteSysParameter(Integer.valueOf(SysParameterId));
    }

    @PostMapping("/updateSysParameterByKey")
    @ApiOperation("通过Key更新Value，已实现")
    public ReturnBean updateSysParameterByKey(@RequestBody Sysparameter sysparameter){
        User tmp=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return sysparameterService.updateSysParameterByKey(sysparameter,tmp.getUserID());
    }
}
