package com.github.dge1992.proxy.dynamic_test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author  dong
 * @create  2018/2/5 13:50
 * @desc 动态代理
 **/
public class DynamicProxy {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Arrays.stream(args).forEach(System.out::println);
                        Object invoke = method.invoke(target, args);
                        System.out.println("结果是:" + invoke);
                        return invoke;
                    }
                }
        );
    }
}
