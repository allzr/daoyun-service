package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.Course;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Teachercourse;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.mapper.TeachercourseMapper;
import com.fzu.edu.daoyun.service.ITeachercourseService;
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
public class TeachercourseServiceImpl extends ServiceImpl<TeachercourseMapper, Teachercourse> implements ITeachercourseService {

    @Autowired
    private TeachercourseMapper teachercourseMapper;

    @Override
    public ReturnBean create(User user, Course course,int year){
        Teachercourse teachercourse=new Teachercourse();
        teachercourse.setCreateTime(LocalDateTime.now());
        teachercourse.setCouID(course.getCouID());
        teachercourse.setUserID(user.getUserID());
        teachercourse.setLastEditorID(user.getUserID());
        teachercourse.setLastEditTime(LocalDateTime.now());
        teachercourse.setOpenYear(year);
        teachercourseMapper.insert(teachercourse);
        return ReturnBean.success("创建成功");
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
        return teachercourseMapper.selectOne(new QueryWrapper<Teachercourse>().eq("teaCouID", teacherCourseId).eq("openYear",LocalDateTime.now().getYear()));
    }
}
