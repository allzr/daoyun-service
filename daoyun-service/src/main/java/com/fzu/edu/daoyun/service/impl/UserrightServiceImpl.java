package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            userright1.setLastEditTime(LocalDateTime.now(ZoneId.of("+08:00")));
            return ReturnBean.success("插入成功");
        }
        userright1.setLastEditorID(userright.getUserID());
        userright1.setLastEditTime(LocalDateTime.now(ZoneId.of("+08:00")));
        if(null!=userright.getCourseManage()) userright1.setCourseManage(userright.getCourseManage());
        if(null!=userright.getDicManage()) userright1.setDicManage(userright.getDicManage());
        if(null!=userright.getManuManage()) userright1.setManuManage(userright.getManuManage());
        if(null!=userright.getSystemManage()) userright1.setSystemManage(userright.getSystemManage());
        if(null!=userright.getUserManage()) userright1.setUserManage(userright.getUserManage());
        if(null!=userright.getRoleManage()) userright1.setRoleManage(userright.getRoleManage());
        userrightMapper.updateById(userright);
        return ReturnBean.success("插入成功");
    }

    @Override
    public ReturnBean selectUserRightByID(int UserID) {
        Userright userright=userrightMapper.selectOne(new QueryWrapper<Userright>().eq("userID",UserID));
        if(null==userright)
            return ReturnBean.error("未查找到该用户");
        return ReturnBean.success("查询成功",userright);
    }

    @Override
    public ReturnBean updateUserRightByID(Userright userright,int userID) {
        Userright userright1=userrightMapper.selectById(userright.getRightID());
        if(null==userright1)
            return ReturnBean.error("未查找到该用户");
        userright1.setLastEditTime(LocalDateTime.now(ZoneId.of("+08:00")));
        userright1.setLastEditorID(userID);
        if(null!=userright.getCourseManage()) userright1.setCourseManage(userright.getCourseManage());
        if(null!=userright.getDicManage()) userright1.setDicManage(userright.getDicManage());
        if(null!=userright.getManuManage()) userright1.setManuManage(userright.getManuManage());
        if(null!=userright.getSystemManage()) userright1.setSystemManage(userright.getSystemManage());
        if(null!=userright.getUserManage()) userright1.setUserManage(userright.getUserManage());
        if(null!=userright.getRoleManage()) userright1.setRoleManage(userright.getRoleManage());
        userrightMapper.updateById(userright1);
        return ReturnBean.success("更新成功");
    }

    @Override
    public void insert(Userright userright){
        userrightMapper.insert(userright);
    }

    @Override
    public ReturnBean selectAll() {
        List<Userright> tmp=userrightMapper.selectList(new QueryWrapper<Userright>());
        List<Map<String , String>> res=new ArrayList<>();
        for(int i=0;i<tmp.size();i++) {
            HashMap<String, String> res1 = new HashMap<>();
            res1.put("rightID",tmp.get(i).getRightID().toString());
            res1.put("manuManage", tmp.get(i).getManuManage().toString());
            res1.put("userManage",tmp.get(i).getUserManage().toString());
            res1.put("dicManage" , tmp.get(i).getDicManage().toString());
            res1.put("systemManage" , tmp.get(i).getSystemManage().toString());
            res1.put("courseManage" , tmp.get(i).getCourseManage().toString());
            res1.put("roleManage" , tmp.get(i).getRoleManage().toString());
            User user = userService.getUserByID(tmp.get(i).getUserID());
            res1.put("username", user.getUsername());
            res1.put("userID", user.getUserID().toString());
            res.add(res1);
        }
        return ReturnBean.success("查询成功",res);
    }

    @Override
    public void deleteByUserId(int userID) {
        Userright userright=userrightMapper.selectOne(new QueryWrapper<Userright>().eq("userID",userID));
        userrightMapper.deleteById(userright.getRightID());
    }
}
