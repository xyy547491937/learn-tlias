package com.itheima.aop;

import com.alibaba.fastjson.JSON;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class LogAspect  {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取usernameid 通过jwt 获取

        String operateUser = JwtUtil.decodeToken(httpServletRequest.getHeader("Authorization"));

        log.info("获取到的token 为 {}",operateUser);
        // operateTime

        LocalDateTime operateTime = LocalDateTime.now();

        // className

        String className = joinPoint.getTarget().getClass().getName();

        //method_name

        String methodName = joinPoint.getSignature().getName();

        // methodParams

        Object[] methodParams = joinPoint.getArgs();
        long begin = System.currentTimeMillis();

        // returnValue
        Object returnValue = joinPoint.proceed();

        String o = JSON.toJSONString(returnValue);

        // 调用 mapper
        long end = System.currentTimeMillis();


        //操作耗时
        long costTime = end-begin;

        OperateLog ol = new OperateLog();


        ol.setOperateUser(Integer.parseInt(operateUser));
        ol.setOperateTime(operateTime);
        ol.setClassName(className);
        ol.setCostTime(costTime);
        ol.setMethodName(methodName);
        ol.setMethodParams(Arrays.toString(methodParams));

        ol.setReturnValue(o);



        operateLogMapper.insert(ol);


        return returnValue;

    }

}
