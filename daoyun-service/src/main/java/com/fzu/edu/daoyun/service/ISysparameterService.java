package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Studentteacou;
import com.fzu.edu.daoyun.entity.Sysparameter;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface ISysparameterService extends IService<Sysparameter> {
    ReturnBean insertSysParameter(Sysparameter sysparameter,int userID);

    ReturnBean selectAll();

    ReturnBean deleteSysParameter(int id);

    ReturnBean updateSysParameterByKey(Sysparameter sysparameter,int userID);

    ReturnBean selectByKey(String Key);
}
