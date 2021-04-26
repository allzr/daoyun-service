package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.entity.Userright;
import com.fzu.edu.daoyun.mapper.UserrightMapper;
import com.fzu.edu.daoyun.service.IUserrightService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Service
public class UserrightServiceImpl extends ServiceImpl<UserrightMapper, Userright> implements IUserrightService {

    @Autowired
    private UserrightMapper userrightMapper;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public ReturnBean insertUserRight(Userright userright) {
        if(null==userright.getUserID())
            return ReturnBean.error("用户ID不能为空");
        Userright userright1=userrightMapper.selectById(userright.getUserID());
        if(null==userright1) {
            if(null==userService.getUserByID(userright.getUserID()))
                return ReturnBean.error("用户不存在");
            userrightMapper.updateById(userright);
            userright1.setLastEditorID(userright.getUserID());
            userright1.setLastEditTime(LocalDateTime.now());
            return ReturnBean.success("插入成功");
        }
        userright1.setLastEditorID(userright.getUserID());
        userright1.setLastEditTime(LocalDateTime.now());
        if(null!=userright.getCourseManage()) userright1.setCourseManage(userright.getCourseManage());
        if(null!=userright.getDicManage()) userright1.setDicManage(userright.getDicManage());
        if(null!=userright.getManuManage()) userright1.setManuManage(userright.getManuManage());
        if(null!=userright.getSystemManage()) userright1.setSystemManage(userright.getSystemManage());
        if(null!=userright.getUserManage()) userright1.setUserManage(userright.getUserManage());
        userrightMapper.updateById(userright);
        return ReturnBean.success("插入成功");
    }

    @Override
    public ReturnBean selectUserRightByID(int UserID) {
        Userright userright=userrightMapper.selectById(UserID);
        if(null==userright)
            return ReturnBean.error("未查找到该用户");
        return ReturnBean.success("查询成功",userright);
    }

    @Override
    public ReturnBean updateUserRightByID(Userright userright,int userID) {
        Userright userright1=userrightMapper.selectById(userright.getUserID());
        if(null==userright1)
            return ReturnBean.error("未查找到该用户");
        userright1.setLastEditTime(LocalDateTime.now());
        userright1.setLastEditorID(userID);
        if(null!=userright.getCourseManage()) userright1.setCourseManage(userright.getCourseManage());
        if(null!=userright.getDicManage()) userright1.setDicManage(userright.getDicManage());
        if(null!=userright.getManuManage()) userright1.setManuManage(userright.getManuManage());
        if(null!=userright.getSystemManage()) userright1.setSystemManage(userright.getSystemManage());
        if(null!=userright.getUserManage()) userright1.setUserManage(userright.getUserManage());
        userrightMapper.updateById(userright1);
        return ReturnBean.success("更新成功");
    }
}
