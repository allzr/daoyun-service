package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Sysparameter;
import com.fzu.edu.daoyun.mapper.SysparameterMapper;
import com.fzu.edu.daoyun.service.ISysparameterService;
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
public class SysparameterServiceImpl extends ServiceImpl<SysparameterMapper,Sysparameter> implements ISysparameterService {

    @Autowired
    private SysparameterMapper sysparameterMapper;

    @Override
    public ReturnBean insertSysParameter(Sysparameter sysparameter, int userID) {
        Sysparameter tmp=sysparameterMapper.selectOne(new QueryWrapper<Sysparameter>().eq("ChineseKey",sysparameter.getChineseKey()));
        if(null!=tmp)
            return ReturnBean.error("该参数已存在");
        sysparameter.setLastEditorID(userID);
        sysparameter.setLastEditTime(LocalDateTime.now());
        sysparameterMapper.insert(sysparameter);
        return ReturnBean.success("插入成功");
    }

    @Override
    public ReturnBean selectAll() {
        return ReturnBean.success("查询成功",sysparameterMapper.selectList(new QueryWrapper<Sysparameter>()));
    }

    @Override
    public ReturnBean deleteSysParameter(int id) {
        sysparameterMapper.deleteById(id);
        return ReturnBean.success("删除成功");
    }

    @Override
    public ReturnBean updateSysParameterByKey(Sysparameter sysparameter,int userID) {
        sysparameter.setLastEditTime(LocalDateTime.now());
        sysparameter.setLastEditorID(userID);
        Sysparameter tmp=sysparameterMapper.selectOne(new QueryWrapper<Sysparameter>().eq("ChineseKey",sysparameter.getChineseKey()));
        if(null==tmp)
            return ReturnBean.error("该参数不存在，更新失败");
        sysparameter.setSysParameterID(tmp.getSysParameterID());
        sysparameterMapper.updateById(sysparameter);
        return ReturnBean.success("更新成功");
    }

    @Override
    public ReturnBean selectByKey(String Key) {
        Sysparameter tmp=sysparameterMapper.selectOne(new QueryWrapper<Sysparameter>().eq("ChineseKey",Key));
        if(null==tmp)
            return ReturnBean.error("该条目不存在");
        return ReturnBean.success("查询成功",tmp);
    }


}
