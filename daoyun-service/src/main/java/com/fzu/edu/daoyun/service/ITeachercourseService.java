package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.Course;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Teachercourse;
import com.fzu.edu.daoyun.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface ITeachercourseService extends IService<Teachercourse> {
    ReturnBean create(User user, Course course,int year);
    ReturnBean getTeaCouID(Teachercourse teachercourse);
    Teachercourse getTeaCouByTeaCouId(int teaCouID);
    List<Map<String , String>> getTeaCouByUserId(int userId);
}
