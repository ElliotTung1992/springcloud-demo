package com.github.dge1992.logaop.aop;

import com.github.dge1992.logaop.annotion.BussinessLog;
import com.github.dge1992.logaop.core.LogObjectHolder;
import com.github.dge1992.logaop.utils.HttpKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

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

        //创建连接
        Jedis conn = new Jedis("192.168.0.135");

        //获取拦截的方法名
        Signature signature = point.getSignature();
        MethodSignature ms = null;
        if (!(signature instanceof MethodSignature)){
            throw new IllegalAccessException("该注解只能用在方法上");
        }
        ms = (MethodSignature) signature;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(ms.getName(), ms.getParameterTypes());

        //获取方法名
        String methodName = currentMethod.getName();

        //获取方法类全路径
        String className = point.getTarget().getClass().getName();
        //获取方法参数值
        Object[] params = point.getArgs();

        //获取BussinessLog注解上的配置的值
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        String bussinessName = annotation.value();

        StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append(param);
            sb.append(" & ");
        }

        //如果涉及到修改，对比变化
        String msg;
        if(bussinessName.indexOf("修改") != -1 || bussinessName.indexOf("编辑") != -1){
            List<String> obj1 = LogObjectHolder.me().get();
            List<String> obj2 = HttpKit.getRequestParametersInLog();

            obj1.stream().forEach(e -> conn.sadd("obj1", e));
            obj2.stream().forEach(e -> conn.sadd("obj2", e));

            Set<String> sdiff = conn.sdiff("obj1", "obj2");
            Set<String> sdiff1 = conn.sdiff("obj2", "obj1");

            msg = "旧值是:" + sdiff.toString() + " 新值是:" + sdiff1.toString();
        }else{
            List<String> obj2 = HttpKit.getRequestParametersInLog();
            msg = "输入的参数是:" + obj2.toString();
        }

        System.out.println(msg);

        //user.getId()
        int id = 5;
//        LogManager.me().executeLog(LogTaskFactory.bussinessLog(5, bussinessName, className, methodName, msg));


    }


}
