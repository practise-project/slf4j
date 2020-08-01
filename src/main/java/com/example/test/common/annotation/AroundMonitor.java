package com.example.test.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 监听方法（用于测试AOP 的@After方法)
 * 使用方法：在service中,需要监听的方法中加入该注解。value值为模块名称
 * @Target  声明这是一个方法
 * @Retention 用来修饰注解，是注解的注解，称为元注解
 *
 * Retention注解有一个属性value，是RetentionPolicy类型的，Enum RetentionPolicy是一个枚举类型，
 * 这个枚举决定了Retention注解应该如何去保持，也可理解为Rentention 搭配 RententionPolicy使用。RetentionPolicy有3个值：CLASS  RUNTIME   SOURCE
 * 按生命周期来划分可分为3类：
 * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
 * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
 * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
 *
 * @author ClowLAY
 * create date 2020/7/31
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AroundMonitor {
    /**
     * 模块名称
     *
     */
    String value();
}
