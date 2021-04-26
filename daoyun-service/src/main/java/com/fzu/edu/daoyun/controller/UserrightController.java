package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Url;
import com.fzu.edu.daoyun.entity.Userright;
import com.fzu.edu.daoyun.service.IUserrightService;
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
@RestController
@RequestMapping("/userright")
public class UserrightController {
    @Autowired
    private IUserrightService userrightService;

    @PostMapping("/insertUserRight")
    @ApiOperation("添加用户权限,创建用户时使用,已实现")
    public ReturnBean insertUserRight(Userright userright)
    {
        return userrightService.insertUserRight(userright);
    }
    @GetMapping("/selectUserRight/{id}")
    @ApiOperation("查询用户权限,已实现,请在URL上附带要查询的user的ID")
    public ReturnBean selectUserRight(@PathVariable int userID){
        return userrightService.selectUserRightByID(userID);
    }
    @PutMapping("/updateUserRight/user/{userID}")
    @ApiOperation("修改用户权限,已实现，请在URL上附带修改执行人的ID")
    public ReturnBean updateUserRight(Userright userright, @PathVariable int userID){
        return userrightService.updateUserRightByID(userright,userID);
    }
}
