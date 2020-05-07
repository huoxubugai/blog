package com.lingfen.website.blog.aop;

import com.lingfen.website.blog.service.VisitorInfoService;
import com.lingfen.website.blog.utils.IpToAddressUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.lingfen.website.blog.controller..*.*(..))") //定义在这个包中及子包的方法，注意有两个点
    public void log(){

    }

    @Pointcut("execution(* com.lingfen.website.blog.controller.*.*(..))") //记录下访问者在首页的一些操作
    public void saveVisitorInfo() {

    }
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        String url =request.getRequestURL().toString();
        String ip=request.getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        String classMethod=joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequstLog requstLog = new RequstLog(url, ip, classMethod, args);
        logger.info("Request:{}",requstLog);
    }


    @After("log()")
    public void doAfter(){
//        logger.info("------------------之后————--——————");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturning(Object result){
        logger.info("result:{}",result);
    }

    private class RequstLog{
        String url ;
        String ip;
        String classMethod;
        Object[] args;

        public RequstLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequstLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
