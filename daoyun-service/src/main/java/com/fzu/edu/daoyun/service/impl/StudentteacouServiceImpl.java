package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.*;
import com.fzu.edu.daoyun.mapper.CourseMapper;
import com.fzu.edu.daoyun.mapper.StudentteacouMapper;
import com.fzu.edu.daoyun.mapper.TeachercourseMapper;
import com.fzu.edu.daoyun.mapper.UserMapper;
import com.fzu.edu.daoyun.service.IStudentteacouService;
import com.fzu.edu.daoyun.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Service
public class StudentteacouServiceImpl extends ServiceImpl<StudentteacouMapper, Studentteacou> implements IStudentteacouService {

    @Autowired
    private StudentteacouMapper studentteacouMapper;
    @Autowired
    private TeachercourseMapper teachercourseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Studentteacou getUserByUserIdAndTeaCouId(int userId, int teaCouId) {
        return studentteacouMapper.selectOne(new QueryWrapper<Studentteacou>().eq("teaCouId", teaCouId).eq("userId", userId));

    }

    @Override
    public Studentteacou getUserBystuCouID(int stuCouId) {
        return studentteacouMapper.selectOne(new QueryWrapper<Studentteacou>().eq("stuCouID", stuCouId));
    }

    @Override
    public ReturnBean joinClass(int userID, String classID) {
        Teachercourse tmp=teachercourseMapper.selectOne(new QueryWrapper<Teachercourse>().eq("classId",classID));
        if(null==tmp)
            return ReturnBean.error("该班课不存在");
        int teaCouID=tmp.getTeaCouID();
        Studentteacou studentteacou=new Studentteacou();
        studentteacou.setCreateTime(LocalDateTime.now(ZoneId.of("+08:00")));
        studentteacou.setEXP(0);
        studentteacou.setUserID(userID);
        studentteacou.setTeaCouID(teaCouID);
        studentteacou.setLastEditorID(userID);
        studentteacou.setLastEditTime(LocalDateTime.now(ZoneId.of("+08:00")));
        studentteacouMapper.insert(studentteacou);
        return ReturnBean.success("加入成功");
    }

    @Override
    public ReturnBean selectByUserId(int userID) {
        List<Studentteacou> studentteacous=studentteacouMapper.selectList(new QueryWrapper<Studentteacou>().eq("userID",userID));
        List<HashMap<String ,String>> res=new ArrayList<>();
        for(int i=0;i<studentteacous.size();i++){
            Studentteacou tmp=studentteacous.get(i);
            HashMap<String,String> tmp1=new HashMap<>();
            Teachercourse teachercourse=teachercourseMapper.selectOne(new QueryWrapper<Teachercourse>().eq("teaCouID",tmp.getTeaCouID()));
            Course course=courseMapper.selectOne(new QueryWrapper<Course>().eq("couID",teachercourse.getCouID()));
            tmp1.put("className", course.getName());
            tmp1.put("createTime",teachercourse.getOpenYear().toString());
            tmp1.put("teacherName",userMapper.selectOne(new QueryWrapper<User>().eq("userID",teachercourse.getUserID())).getUsername());
            tmp1.put("classID",teachercourse.getClassId());
            tmp1.put("id",teachercourse.getTeaCouID().toString());
            res.add(tmp1);
        }
        return ReturnBean.success("查询成功",res);
    }

    @Override
    public ReturnBean selectByClassId(String ClassID) {
        Teachercourse teachercourse=teachercourseMapper.selectOne(new QueryWrapper<Teachercourse>().eq("classID",ClassID));
        if(null==teachercourse)
            return ReturnBean.error("该班课不存在");
        int teaCouID=teachercourse.getTeaCouID();
        List<HashMap<String,String>> res=new ArrayList<>();
        List<Studentteacou> studentteacous=studentteacouMapper.selectList(new QueryWrapper<Studentteacou>().eq("teaCouID", teaCouID));
        for(int i=0;i<studentteacous.size();i++){
            Studentteacou tmp=studentteacous.get(i);
            User user=userMapper.selectOne(new QueryWrapper<User>().eq("userID",tmp.getUserID()));
            HashMap<String,String> tmp1=new HashMap<>();
            tmp1.put("username",user.getUsername());
            tmp1.put("studentNumber",user.getStuTeaAdmNumber());
            tmp1.put("EXP",tmp.getEXP().toString());
            res.add(tmp1);
        }
        return ReturnBean.success("查询成功",res);
    }
}
