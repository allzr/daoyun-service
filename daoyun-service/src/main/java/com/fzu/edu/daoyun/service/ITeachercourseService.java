package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
    ReturnBean getTeaCouInfo(String classID);
    ReturnBean selectAll();
    ReturnBean delete(int id);
    ReturnBean insert(User user, Course course,int year,User teacher);
}
