package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.Course;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;
import com.fzu.edu.daoyun.mapper.CourseMapper;
import com.fzu.edu.daoyun.service.ICourseService;
import com.fzu.edu.daoyun.service.IUserService;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private IUserService userService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private TeachercourseServiceImpl teachercourseService;

    @Override
    public ReturnBean createCourse(User user, Course course,int year) {
        if(2!=user.getUserType()){
            return ReturnBean.error("非老师用户无法创建班课");
        }
        Course course1=getCourseByName(course.getName());
        if(null==course1){
            course.setLastEditTime(LocalDateTime.now());
            course.setLastEditorID(user.getUserID());
            courseMapper.insert(course);
        }
        else if(true==course1.getIsDelete())
        {
            course1.setLastEditorID(user.getUserID());
            course1.setLastEditTime(LocalDateTime.now());
            course1.setIsDelete(false);
        }
        return teachercourseService.create(user,course,year);
    }

    @Override
    public ReturnBean deleteCourse(String id) {
        Course course=getById(Integer.valueOf(id));
        course.setIsDelete(true);
        courseMapper.updateById(course);
        return ReturnBean.success("删除成功");
    }

    public Course getCourseByName(String name)
    {
        return courseMapper.selectOne(new QueryWrapper<Course>().eq("name",name).eq("isDelete",false));
    }
}
