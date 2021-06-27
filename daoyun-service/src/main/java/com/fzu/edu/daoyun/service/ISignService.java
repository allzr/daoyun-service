package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Sign;
import com.fzu.edu.daoyun.entity.SignDetail;
import com.fzu.edu.daoyun.entity.Teachercourse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface ISignService extends IService<Sign> {

    List<Sign> getBeginList(int teaCouID);
    List<Sign> getStudentSignInfoByBeginTime(int teacouID, LocalDateTime beginTime);
    Sign getBegin(int teaCouID);

    ReturnBean beginSign(SignDetail signDetail);

    ReturnBean beginSignByTime(SignDetail signDetail);

    ReturnBean studentSign(SignDetail signDetail);

    ReturnBean getSignCreateTime(int teaCouID);

    ReturnBean getSignInfo(SignDetail signDetail);

    ReturnBean finishSign(SignDetail signDetail);

    Sign getNowSign(int teaCouID);

    boolean checkSignOrNot(SignDetail signDetail);

    ReturnBean getSignInfoBySignID(int signID);
}
