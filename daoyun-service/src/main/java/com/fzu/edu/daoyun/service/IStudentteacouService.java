package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Studentteacou;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface IStudentteacouService extends IService<Studentteacou> {
    Studentteacou getUserByUserIdAndTeaCouId(int UserId, int TeaCouId);
    Studentteacou getUserBystuCouID(int stuCouId);
    ReturnBean joinClass(int userID, String classID);
    ReturnBean selectByUserId(int userID);
    ReturnBean selectByClassId(String ClassID);
}
