package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.Datadicdetail;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.mapper.DatadicdetailMapper;
import com.fzu.edu.daoyun.service.IDatadicdetailService;
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
public class DatadicdetailServiceImpl extends ServiceImpl<DatadicdetailMapper, Datadicdetail> implements IDatadicdetailService {

    private DatadicdetailMapper datadicdetailMapper;

    @Override
    public ReturnBean insertDatadicdetail(Datadicdetail datadicdetail) {
        Datadicdetail tmp=datadicdetailMapper.selectOne(new QueryWrapper<Datadicdetail>().eq("dataDicID",datadicdetail.getDataDicID()).eq("dataValue",datadicdetail.getDataValue()));
        if(null!=tmp)
            return ReturnBean.error("该条目已存在");
        datadicdetailMapper.insert(datadicdetail);
        return ReturnBean.success("插入成功");
    }

    @Override
    public ReturnBean deleteDatadicdetail(int id) {
        Datadicdetail tmp=datadicdetailMapper.selectById(id);
        if(null==tmp)
            return ReturnBean.error("该条目不存在");

        return null;
    }

    @Override
    public ReturnBean selectDatadicdetailById(int id) {
        return null;
    }

    @Override
    public ReturnBean selectAll() {
        return null;
    }

    @Override
    public ReturnBean selectDataDicdetailByDatadicId(int DatadicId) {
        return null;
    }

    @Override
    public ReturnBean updateDatadicdetail(Datadicdetail datadicdetail) {
        return null;
    }
}
