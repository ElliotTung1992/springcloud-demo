package com.github.dge1992.logaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author  dong
 * @create  2018/2/5 17:04
 * @desc 日志记录
 **/
@Aspect
@Component
public class LogAop {

    /**
     * 定义切点
     */
    @Pointcut(value = "@annotation(com.github.dge1992.logaop.annotion.BussinessLog)")
    public void cutService(){

    }

    /**
     * 环绕通知, 增强处理
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("cutService()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        try{
            handle(point);
        }catch (Exception e){
            System.out.println("出错啦！！！");
        }
        return result;
    }

    /**
     * 处理业务
     * @param point
     */
    private void handle(ProceedingJoinPoint point) throws Exception {
        //获取方法名
        Signature signature = point.getSignature();
        MethodSignature ms = null;
        if (!(signature instanceof MethodSignature)){
            throw new IllegalAccessException("该注解只能用在方法上");
        }
        ms = (MethodSignature) signature;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(ms.getName(), ms.getParameterTypes());
        //获取方法名
        String currentName = currentMethod.getName();

        String className = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

    }


}
