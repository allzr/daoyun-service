package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Teachercourse;
import com.fzu.edu.daoyun.entity.Url;
import com.fzu.edu.daoyun.service.IUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IUrlService urlService;

    @PostMapping("/insertUrl/user/{userID}")
    @ApiOperation("添加URL，已实现")
    public ReturnBean insertUrl(@RequestBody Url url,@PathVariable  int userID)
    {
        return urlService.insertUrl(url,userID);
    }
    @GetMapping("/selectUrl/{urlID}")
    @ApiOperation("查询URL，已实现")
    public ReturnBean selectUrl(@PathVariable String urlID){
        return urlService.selectUrl(urlID);
    }

    @PutMapping("/updateUrl/{userID}")
    @ApiOperation("修改URL，已实现")
    public ReturnBean updateUrl(@RequestBody Url url, @PathVariable String userID){
        return urlService.updateUrl(url,Integer.valueOf(userID));
    }
    @GetMapping("selectAll")
    @ApiOperation("查询所有Url")
    public ReturnBean selectAll(){
        return urlService.selectAll();
    }
}
