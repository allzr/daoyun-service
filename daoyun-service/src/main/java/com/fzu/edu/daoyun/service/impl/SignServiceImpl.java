package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.*;
import com.fzu.edu.daoyun.mapper.SignMapper;
import com.fzu.edu.daoyun.mapper.StudentteacouMapper;
import com.fzu.edu.daoyun.mapper.UserMapper;
import com.fzu.edu.daoyun.service.ISignService;
import com.fzu.edu.daoyun.service.IStudentteacouService;
import com.fzu.edu.daoyun.service.ITeachercourseService;
import com.fzu.edu.daoyun.service.IUserService;
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
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign> implements ISignService {
    @Autowired
    private SignMapper signMapper;
    @Autowired
    private ITeachercourseService teachercourseService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IStudentteacouService studentTeaCouService;
    @Autowired
    private StudentteacouMapper studentteacouMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ReturnBean beginSign(SignDetail signDetail){
        Teachercourse teachercourse=teachercourseService.getTeaCouByTeaCouId(signDetail.getTeaCouID());
        if(null==teachercourse)
            return ReturnBean.error("班课不存在");
        User user=userService.getUserByID(teachercourse.getUserID());
        if(null==user)
            return ReturnBean.error("用户不存在");
        if(2!=user.getUserType())
            return ReturnBean.error("非老师用户不能发起签到");
        if(null!=getNowSign(signDetail.getTeaCouID()))
            return ReturnBean.error("有未结束的签到");
        if(0==signDetail.getTime())
            signDetail.setTime(60);
        Sign sign=new Sign();
        sign.setSignTime(LocalDateTime.now(ZoneId.of("+08:00")).plusMinutes(signDetail.getTime()));
        sign.setCreateTime(LocalDateTime.now(ZoneId.of("+08:00")));
        sign.setTeaCouID(signDetail.getTeaCouID());
        sign.setSignLocationX(signDetail.getLocationX());
        sign.setSignLocationY(signDetail.getLocationY());
        signMapper.insert(sign);
        return ReturnBean.success("开始签到");
    }

    @Override
    public ReturnBean beginSignByTime(SignDetail signDetail){
        Teachercourse teachercourse=teachercourseService.getTeaCouByTeaCouId(signDetail.getTeaCouID());
        if(null==teachercourse)
            return ReturnBean.error("班课不存在");
        User user=userService.getUserByID(teachercourse.getUserID());
        if(null==user)
            return ReturnBean.error("用户不存在");
        if(2!=user.getUserType())
            return ReturnBean.error("非老师用户不能发起签到");
        if(null!=getNowSign(signDetail.getTeaCouID()))
            return ReturnBean.error("有未结束的签到");
        signDetail.setCreateTime(LocalDateTime.now(ZoneId.of("+08:00")));
        Sign sign=new Sign();
        sign.setSignTime(LocalDateTime.now(ZoneId.of("+08:00")).plusMinutes(signDetail.getTime()));
        sign.setCreateTime(LocalDateTime.now(ZoneId.of("+08:00")));
        sign.setTeaCouID(signDetail.getTeaCouID());
        sign.setSignLocationX(signDetail.getLocationX());
        sign.setSignLocationY(signDetail.getLocationY());
        signMapper.insert(sign);
        return ReturnBean.success("开始签到");
    }

    @Override
    public ReturnBean studentSign(SignDetail signDetail){
        Studentteacou studentteacou = studentTeaCouService.getUserByUserIdAndTeaCouId(Integer.valueOf(signDetail.getUserID()),Integer.valueOf(signDetail.getTeaCouID()));
        if(null== studentteacou)
            return ReturnBean.error("学生或班级信息不存在");
        //Teachercourse teachercourse=teachercourseService.getTeacherCourseByTeacherCourseId(signDetail.getTeaCouID());
        Sign sign=getBegin(studentteacou.getTeaCouID());
        if(null==sign||LocalDateTime.now(ZoneId.of("+08:00")).isAfter(sign.getSignTime()))
            return ReturnBean.error("签到未开放");
        if(!checkSignOrNot(signDetail)) return ReturnBean.error("请勿重复签到");
        Sign sign1=new Sign();
        sign1.setTeaCouID(studentteacou.getTeaCouID());
        sign1.setStuTeaCouID(studentteacou.getStuTeaCouID());
        sign1.setCreateTime(sign.getCreateTime());
        sign1.setSignLocationX(signDetail.getLocationX());
        sign1.setSignLocationY(signDetail.getLocationY());
        sign1.setSignTime(LocalDateTime.now(ZoneId.of("+08:00")));
        signMapper.insert(sign1);
        User user=userMapper.selectById(studentteacou.getUserID());
        user.setExp(user.getExp()+2);
        userMapper.updateById(user);
        studentteacou.setEXP(studentteacou.getEXP()+2);
        studentteacouMapper.updateById(studentteacou);
        return ReturnBean.success("签到成功");
    }



    @Override
    public ReturnBean getSignCreateTime(int teaCouID){
        return ReturnBean.success("读取成功",getBeginList(teaCouID));
    }

    @Override
    public ReturnBean getSignInfo(SignDetail signDetail){
        return ReturnBean.success("读取成功",getStudentSignInfoByBeginTime(signDetail.getTeaCouID(),signDetail.getCreateTime()));
    }

    @Override
    public ReturnBean finishSign(SignDetail signDetail) {
        Sign sign=getNowSign(signDetail.getTeaCouID());
        if(null==sign)
            return ReturnBean.error("没有可以结束的签到");
        sign.setSignTime(LocalDateTime.now(ZoneId.of("+08:00")));
        signMapper.updateById(sign);
        return ReturnBean.success("结束成功");
    }

    @Override
    public Sign getNowSign(int teaCouID) {
        if(0==getBeginList(teaCouID).size())
            return null;
        List<Sign> signs=getBeginList(teaCouID);
        if(0==signs.size())return null;
        signs.sort(Comparator.comparing(Sign::getCreateTime).reversed());
        Sign tmp=signs.get(0);
        if(tmp.getSignTime().isAfter(LocalDateTime.now(ZoneId.of("+08:00"))))
            return tmp;
        return null;
    }

    @Override
    public boolean checkSignOrNot(SignDetail signDetail) {
        Studentteacou studentteacou= studentTeaCouService.getUserByUserIdAndTeaCouId(signDetail.getUserID(),signDetail.getTeaCouID());
        Sign sign=getNowSign(signDetail.getTeaCouID());
        List<Sign> signs=signMapper.selectList(new QueryWrapper<Sign>().eq("stuTeaCouID",studentteacou.getStuTeaCouID()));
        if(0==signs.size()) return true;
        signs.sort(Comparator.comparing(Sign::getSignTime).reversed());
        if(signs.get(0).getCreateTime().equals(sign.getCreateTime()))
            return false;
        return true;
    }

    @Override
    public ReturnBean getSignInfoBySignID(int signID) {
        Sign sign=signMapper.selectById(signID);
        if(null==sign) return ReturnBean.error("该记录不存在");
        if(null!=sign.getStuTeaCouID()) return ReturnBean.error("查询错误，该签到记录为学生签到记录");
        List<Sign> signs=new LinkedList<>();
        if(null!=sign.getSignTime())
            signs=signMapper.selectList(new QueryWrapper<Sign>().eq("teaCouID",sign.getTeaCouID()).gt("signTime",sign.getCreateTime()).lt("signTime",sign.getSignTime()));
        else signs=signMapper.selectList(new QueryWrapper<Sign>().eq("teaCouID",sign.getTeaCouID()).gt("signTime",sign.getCreateTime()));
        List<User> res=new LinkedList<>();
        for(int i=0;i<signs.size();i++){
            Sign tmp=signs.get(i);
            Studentteacou studentteacou=studentteacouMapper.selectById(tmp.getStuTeaCouID());
            User user=userMapper.selectById(studentteacou.getUserID());
            user.setPassword(null);
            res.add(user);
        }
        return ReturnBean.success("查询成功",res);
    }

    @Override
    public List<Sign> getBeginList(int teaCouID) {
        List<Sign> sign=signMapper.selectList(new QueryWrapper<Sign>().eq("teaCouID",teaCouID));
        for(int i=0;i<sign.size();i++){
            if(null!=sign.get(i).getStuTeaCouID()) {
                sign.remove(i);
                i--;
            }
        }
        return sign;
    }

    @Override
    public List<Sign> getStudentSignInfoByBeginTime(int teaCouID, LocalDateTime beginTime) {
        List<Sign> result=signMapper.selectList(new QueryWrapper<Sign>().eq("createTime",beginTime).eq("teaCouID",teaCouID));
        for(int i=0;i<result.size();i++){
            if(null==result.get(i).getStuTeaCouID()) {
                result.remove(i);
                i--;
            }
        }
        return result;
    }

    @Override
    public Sign getBegin(int teaCouID) {
        List<Sign> signs=signMapper.selectList(new QueryWrapper<Sign>().eq("teaCouID",teaCouID));
        for(int i=0;i<signs.size();i++){
            if(null!=signs.get(i).getStuTeaCouID()) {
                signs.remove(i);
                i--;
            }
        }
        signs.sort(Comparator.comparing(Sign::getSignTime).reversed());
        if(0==signs.size()) return null;
        return signs.get(0);
    }
}
