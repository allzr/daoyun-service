package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.Datadic;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface IDatadicService extends IService<Datadic> {
    ReturnBean insertDatadic(Datadic datadic,int userID);

    ReturnBean updateDatadic(Datadic datadic, int userID);

    ReturnBean selectDatadicByKey(String key);

    ReturnBean selectAll();

    ReturnBean deleteDatadic(int DatadicID, int userID);

    public Datadic getDatadicByKey(String DatadicKey);
}
