package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.Course;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface ICourseService extends IService<Course> {

    ReturnBean createCourse(User user, Course course, int year);

    ReturnBean deleteCourse(String id);

    ReturnBean selectCourseById(String id);

    Course getCourseByName(String name);

    String getCourseNameById(int id);
}
