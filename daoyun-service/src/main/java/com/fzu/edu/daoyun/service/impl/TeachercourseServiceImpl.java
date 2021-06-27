package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.*;
import com.fzu.edu.daoyun.mapper.TeachercourseMapper;
import com.fzu.edu.daoyun.service.ITeachercourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Service
public class TeachercourseServiceImpl extends ServiceImpl<TeachercourseMapper, Teachercourse> implements ITeachercourseService {

    @Autowired
    private TeachercourseMapper teachercourseMapper;
    @Autowired
    private CourseServiceImpl courseService;
    @Autowired
    private UserServiceImpl userService;

    @Override
    public ReturnBean create(User user, Course course,int year){
        Teachercourse teachercourse=new Teachercourse();
        teachercourse.setCreateTime(LocalDateTime.now(ZoneId.of("+08:00")));
        teachercourse.setCouID(course.getCouID());
        teachercourse.setUserID(user.getUserID());
        teachercourse.setLastEditorID(user.getUserID());
        teachercourse.setLastEditTime(LocalDateTime.now(ZoneId.of("+08:00")));
        teachercourse.setOpenYear(year);
        String classID="";
        Random random = new Random();
        do {
            classID="";
            for (int i = 0; i < 8; i++) {
                classID += random.nextInt(10);
            }
        }while(null!=teachercourseMapper.selectOne(new QueryWrapper<Teachercourse>().eq("ClassID",classID)));
        teachercourse.setClassId(classID);
        teachercourseMapper.insert(teachercourse);
        return ReturnBean.success("创建成功",teachercourse);
    }

    @Override
    public ReturnBean insert(User user, Course course,int year,User teacher){
        Teachercourse teachercourse=new Teachercourse();
        teachercourse.setCreateTime(LocalDateTime.now(ZoneId.of("+08:00")));
        teachercourse.setCouID(course.getCouID());
        teachercourse.setUserID(teacher.getUserID());
        teachercourse.setLastEditorID(user.getUserID());
        teachercourse.setLastEditTime(LocalDateTime.now(ZoneId.of("+08:00")));
        teachercourse.setOpenYear(year);
        String classID="";
        Random random = new Random();
        do {
            classID="";
            for (int i = 0; i < 8; i++) {
                classID += random.nextInt(10);
            }
        }while(null!=teachercourseMapper.selectOne(new QueryWrapper<Teachercourse>().eq("ClassID",classID)));
        teachercourse.setClassId(classID);
        teachercourseMapper.insert(teachercourse);
        return ReturnBean.success("创建成功",teachercourse);
    }

    @Override
    public ReturnBean getTeaCouID(Teachercourse teachercourse) {
        String tmp=String.valueOf(teachercourse.getTeaCouID());
        String id=new String();
        for(int i=tmp.length();i<6;i++){
            id+="0";
        }
        id+=tmp;
        return ReturnBean.success("获取成功",id);
    }



    @Override
    public Teachercourse getTeaCouByTeaCouId(int teacherCourseId) {
        return teachercourseMapper.selectOne(new QueryWrapper<Teachercourse>().eq("teaCouID", teacherCourseId));
    }

    @Override
    public List<Map<String , String>> getTeaCouByUserId(int userId) {
        List<Teachercourse> tmp=teachercourseMapper.selectList(new QueryWrapper<Teachercourse>().eq("userID", userId));
        List<Map<String , String>> res=new ArrayList<>();
        for(int i=0;i<tmp.size();i++) {
            HashMap<String, String> res1 = new HashMap<>();
            res1.put("classID", tmp.get(i).getClassId());
            res1.put("id",tmp.get(i).getTeaCouID().toString());
            res1.put("className", courseService.getCourseNameById(tmp.get(i).getCouID()));
            User user = userService.getUserByID(tmp.get(i).getUserID());
            res1.put("teacherName", user.getUsername());
            res1.put("createTime", tmp.get(i).getOpenYear().toString());
            res.add(res1);
        }

        return res;
    }

    @Override
    public ReturnBean getTeaCouInfo(String classID) {
        Teachercourse teachercourse = teachercourseMapper.selectOne(new QueryWrapper<Teachercourse>().eq("classID", classID));
        Map<String,String> res = new HashMap<>();
        if(null==teachercourse)
            return ReturnBean.error("该班课不存在");
        res.put("className",courseService.getCourseNameById(teachercourse.getCouID()));
        res.put("teacherName",userService.getUserByID(teachercourse.getUserID()).getUsername());
        res.put("createTime",teachercourse.getOpenYear().toString());
        return ReturnBean.success("查询成功",res);
    }

    @Override
    public ReturnBean selectAll() {
        List<Teachercourse> tmp=teachercourseMapper.selectList(new QueryWrapper<>());
        List<Map<String , String>> res=new ArrayList<>();
        for(int i=0;i<tmp.size();i++) {
            HashMap<String, String> res1 = new HashMap<>();
            res1.put("classID", tmp.get(i).getClassId());
            res1.put("id",tmp.get(i).getTeaCouID().toString());
            res1.put("className", courseService.getCourseNameById(tmp.get(i).getCouID()));
            User user = userService.getUserByID(tmp.get(i).getUserID());
            res1.put("teacherName", user.getUsername());
            res1.put("createTime", tmp.get(i).getOpenYear().toString());
            res.add(res1);
        }
        return ReturnBean.success("查询成功",res);
    }

    @Override
    public ReturnBean delete(int id) {
        teachercourseMapper.deleteById(id);
        return ReturnBean.success("删除成功");
    }


}
