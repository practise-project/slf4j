package com.example.test.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 监听方法（用于测试AOP 的@Before方法)
 * @author ClowLAY
 * create date 2020/8/1
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeforeMonitor {

    String value();
}
