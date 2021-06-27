package com.fzu.edu.daoyun.controller;


import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Url;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.entity.Userright;
import com.fzu.edu.daoyun.service.IUserrightService;
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
@RestController
@RequestMapping("/userright")
public class UserrightController {
    @Autowired
    private IUserrightService userrightService;

    @PostMapping("/insertUserRight")
    @ApiOperation("添加用户权限,创建用户时使用,已实现")
    public ReturnBean insertUserRight(@RequestBody Userright userright)
    {
        return userrightService.insertUserRight(userright);
    }
    @GetMapping("/selectUserRight/{userID}")
    @ApiOperation("查询用户权限,已实现,请在URL上附带要查询的user的ID")
    public ReturnBean selectUserRight(@PathVariable String userID){
        return userrightService.selectUserRightByID(Integer.valueOf(userID));
    }
    @GetMapping("/selectLoadingUserRight")
    @ApiOperation("查询当前登录的用户权限,已实现")
    public ReturnBean selectLoadingUserRight(){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userrightService.selectUserRightByID(Integer.valueOf(user.getUserID()));
    }
    @GetMapping("/selectAll")
    @ApiOperation("查询所有用户权限,已实现")
    public ReturnBean selectUserRight(){
        return userrightService.selectAll();
    }
    @PutMapping("/updateUserRight/user/{userID}")
    @ApiOperation("修改用户权限,已实现，请在URL上附带修改执行人的ID")
    public ReturnBean updateUserRight(@RequestBody Userright userright, @PathVariable String userID){
        return userrightService.updateUserRightByID(userright,Integer.valueOf(userID));
    }
}
