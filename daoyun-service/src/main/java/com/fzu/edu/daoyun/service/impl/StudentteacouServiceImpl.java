package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.Studentteacou;
import com.fzu.edu.daoyun.mapper.StudentteacouMapper;
import com.fzu.edu.daoyun.service.IStudentteacouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Studentteacou getUserByUserIdAndTeaCouId(int userId, int teaCouId) {
        return studentteacouMapper.selectOne(new QueryWrapper<Studentteacou>().eq("teaCouId", teaCouId).eq("userId", userId));

    }

    @Override
    public Studentteacou getUserBystuCouID(int stuCouId) {
        return studentteacouMapper.selectOne(new QueryWrapper<Studentteacou>().eq("stuCouID", stuCouId));
    }
}
