package com.fzu.edu.daoyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Url;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
public interface IUrlService extends IService<Url> {
    ReturnBean insertUrl(Url url, int userID);
    ReturnBean selectUrl(String id);
    ReturnBean updateUrl(Url url,int userID);
}
