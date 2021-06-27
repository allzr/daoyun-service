package com.fzu.edu.daoyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzu.edu.daoyun.entity.ReturnBean;
import com.fzu.edu.daoyun.entity.Url;
import com.fzu.edu.daoyun.mapper.UrlMapper;
import com.fzu.edu.daoyun.service.IUrlService;
import com.fzu.edu.daoyun.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xing
 * @since 2021-04-12
 */
@Service
public class UrlServiceImpl extends ServiceImpl<UrlMapper, Url> implements IUrlService {

    @Autowired
    private UrlMapper urlMapper;
    @Autowired
    private IUserService userService;

    @Override
    public ReturnBean insertUrl(Url url,int userID) {
        if(null==url.getUrl())
            return ReturnBean.error("Url不能为空");
        if(null==userService.getUserByID(userID))
            return ReturnBean.error("用户不存在");
        url.setLastEditorID(userID);
        url.setLastEditTime(LocalDateTime.now(ZoneId.of("+08:00")));
        urlMapper.insert(url);
        return ReturnBean.success("插入成功");
    }

    @Override
    public ReturnBean selectUrl(String id) {
        Url url= urlMapper.selectById(id);
        if(null==url)
            return ReturnBean.error("该ID不存在");
        return ReturnBean.success("查询成功",url);
    }

    @Override
    public ReturnBean updateUrl(Url url,int userID) {
        if(null==url.getUrl()&&null==url.getUrlName())
            return ReturnBean.error("Url和UrlName不能同时为空");
        if(null==userService.getUserByID(userID))
            return ReturnBean.error("用户不存在");
        Url tmp=urlMapper.selectById(url.getUrlID());
        if(null==url.getUrl())
            url.setUrl(tmp.getUrl());
        if(null==url.getUrlName())
            url.setUrlName(tmp.getUrlName());
        url.setLastEditTime(LocalDateTime.now(ZoneId.of("+08:00")));
        url.setLastEditorID(userID);
        urlMapper.updateById(url);
        return ReturnBean.success("Url信息更新成功");
    }

    @Override
    public ReturnBean selectAll() {
        return ReturnBean.success("查询成功",urlMapper.selectList(new QueryWrapper<Url>()));
    }
}
