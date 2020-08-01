package com.example.test.common.aspect;

import com.example.test.common.annotation.AroundMonitor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 环绕执行
 * @author ClowLAY
 * create date 2020/7/31
 */
@Aspect
@Component
public class AroundAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(AroundAspect.class);


    //// 指定注解
    @Pointcut("@annotation(com.example.test.common.annotation.AroundMonitor)")
    public void pointCut(){}



    @Around(value="pointCut() && @annotation(aroundMonitor)")
    public JsonNode around(ProceedingJoinPoint joinPoint,AroundMonitor aroundMonitor){
        var result = new ObjectMapper().createObjectNode();

        //获取输入参数
        final Object[] args = joinPoint.getArgs();

        LOGGER.info("监听方法为:{},传递参数为：{}",aroundMonitor.value(),args[0]);
        //让代理方法先执行
        try {
            result = (ObjectNode) joinPoint.proceed();

            LOGGER.info("返回参数为 :{}",result.toString());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

}
