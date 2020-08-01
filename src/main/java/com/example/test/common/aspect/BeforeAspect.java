package com.example.test.common.aspect;

import com.example.test.common.annotation.AroundMonitor;
import com.example.test.common.annotation.BeforeMonitor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 在方法之前执行
 * @author ClowLAY
 * create date 2020/8/1
 */
@Aspect
@Component
public class BeforeAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAspect.class);


    //// 指定注解
    @Pointcut("@annotation(com.example.test.common.annotation.BeforeMonitor)")
    public void pointCut(){}



    @Before(value="pointCut() && @annotation(beforeMonitor)")
    public void before( BeforeMonitor beforeMonitor){

        LOGGER.info("value：{}",beforeMonitor.value());


    }
}
