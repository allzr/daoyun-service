package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.Datadic;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.mapper.DatadicMapper;
import com.fzu.edu.daoyun.service.IDatadicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Service
public class DatadicServiceImpl extends ServiceImpl<DatadicMapper, Datadic> implements IDatadicService {

    @Autowired
    private DatadicMapper datadicMapper;


    @Override
    public ReturnBean insertDatadic(Datadic datadic, int userID) {
        Datadic tmp=datadicMapper.selectOne(new QueryWrapper<Datadic>().eq("dataKey",datadic).eq("isDelete",false));
        if(null!=tmp&&false==tmp.getIsDelete())
            ReturnBean.error("该数据已存在");
        datadic.setCreateTime(LocalDateTime.now());
        datadic.setLastEditorID(userID);
        datadic.setLastEditTime(LocalDateTime.now());
        if(null!=tmp) {
            datadic.setDataDicID(tmp.getDataDicID());
            datadicMapper.updateById(datadic);
        }
        datadicMapper.insert(datadic);
        return ReturnBean.success("添加成功");
    }

    @Override
    public ReturnBean updateDatadic(Datadic datadic, int userID) {
        Datadic tmp=datadicMapper.selectById(datadic.getDataDicID());
        if(null==tmp||true==tmp.getIsDelete())
            return ReturnBean.error("该数据不存在");
        if(null==datadic.getDataKey())
            return ReturnBean.error("关键字不能为空");
        tmp.setDataKey(datadic.getDataKey());
        tmp.setLastEditTime(LocalDateTime.now());
        tmp.setLastEditorID(userID);
        datadicMapper.updateById(datadic);
        return ReturnBean.success("更新成功");
    }

    @Override
    public ReturnBean selectDatadicByKey(String key) {
        Datadic datadic=datadicMapper.selectOne(new QueryWrapper<Datadic>().eq("dataKey",key).eq("isDelete",false));
        if(null==datadic)
            return ReturnBean.error("查找无结果");
        return ReturnBean.success("查找成功",datadic);
    }

    @Override
    public ReturnBean selectAll() {
        List<Datadic> datadics=datadicMapper.selectList(new QueryWrapper<Datadic>());
        if(0==datadics.size())
            return ReturnBean.error("查询无结果");
        return ReturnBean.success("查询成功",datadics);
    }

    @Override
    public ReturnBean deleteDatadic(int DatadicID, int userID) {
        Datadic tmp=getById(DatadicID);
        if(null==tmp)
            return ReturnBean.error("删除失败，未查找到该ID");
        tmp.setIsDelete(true);
        tmp.setLastEditorID(userID);
        tmp.setLastEditTime(LocalDateTime.now());
        datadicMapper.updateById(tmp);
        return ReturnBean.success("删除成功");
    }
}
