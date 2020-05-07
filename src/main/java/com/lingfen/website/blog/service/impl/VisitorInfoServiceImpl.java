package com.lingfen.website.blog.service.impl;

import com.lingfen.website.blog.bean.VisitorInfo;
import com.lingfen.website.blog.mapper.VisitorInfoMapper;
import com.lingfen.website.blog.service.VisitorInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VisitorInfoServiceImpl implements VisitorInfoService {
    @Autowired
    VisitorInfoMapper visitorInfoMapper;

    @Override
    public int saveVisitorInfo(String ip, String cityInfo) {
        Integer id = visitorInfoMapper.searchIpIsExist(ip);
        int insertResult = 0;
        int updateResult = 0;
        if (id == null) {
            //说明不存在当前ip，需要写入
            VisitorInfo visitorInfo = new VisitorInfo();
            visitorInfo.setIp(ip);
            visitorInfo.setAddressInfo(cityInfo);
            visitorInfo.setVisitTimes(1);
            visitorInfo.setTime(new Date());
            insertResult = visitorInfoMapper.insertSelective(visitorInfo);
        } else {
            //存在 只需更新访问次数和访问时间即可
            Date updateTime = new Date();
            updateResult = visitorInfoMapper.updateVisitTimes(id, updateTime);
        }
        return insertResult;
    }
}
