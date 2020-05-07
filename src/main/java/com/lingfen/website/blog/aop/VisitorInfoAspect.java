package com.lingfen.website.blog.aop;

import com.lingfen.website.blog.service.VisitorInfoService;
import com.lingfen.website.blog.utils.IpToAddressUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class VisitorInfoAspect {
    @Autowired
    VisitorInfoService visitorInfoService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.lingfen.website.blog.controller.*.*(..))") //记录下访问者在首页的一些操作
    public void saveVisitorInfo() {

    }

    @Before("saveVisitorInfo()")
    public void before() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ip = request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        long time = System.currentTimeMillis();
        String cityInfo = IpToAddressUtil.getCityInfo(ip);
        if (cityInfo != null) {
            long endtime = System.currentTimeMillis() - time;
            logger.info("通过ip查询城市信息的时间花费（ms）：" + endtime);
            //保存进数据库
            int insertLine = visitorInfoService.saveVisitorInfo(ip, cityInfo);
            if (insertLine != 0) {
                logger.info("目前不存在该ip信息，现已插入数据库：" + ip);
            } else {
                logger.info("存在该ip信息，对访问次数进行更新");
            }
        } else {
            logger.info("请求ip的位置信息出错");
        }
    }
}
