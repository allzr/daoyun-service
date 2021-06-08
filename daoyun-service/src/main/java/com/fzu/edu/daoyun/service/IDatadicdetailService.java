package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.Datadicdetail;
import com.fzu.edu.daoyun.entity.ReturnBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface IDatadicdetailService extends IService<Datadicdetail> {
    ReturnBean insertDatadicdetail(Datadicdetail datadicdetail);

    ReturnBean deleteDatadicdetail(int id);

    ReturnBean selectDatadicdetailById(int id);

    ReturnBean selectAll();

    ReturnBean selectDataDicdetailByDatadicId(int DatadicId);

    ReturnBean updateDatadicdetail(Datadicdetail datadicdetail);
}
