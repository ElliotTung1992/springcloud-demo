package com.github.dge1992.logaop.annotion;


import java.lang.annotation.*;

/**
 *
 * @Retention: 定义注解的保留策略

 @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
 @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
 @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到

 @Retention
 @Target({ElementType.FIELD,ElementType.METHOD})//定义注解的作用目标**作用范围字段、枚举的常量/方法

 */

/**
 * @author  dong
 * @create  2018/2/5 16:58
 * @desc 标记需要记录日志方法的注解
 **/
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BussinessLog {

    /**
     * 业务名称
     * @return
     */
    String value() default "";

}
