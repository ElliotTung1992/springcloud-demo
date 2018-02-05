package com.github.dge1992.proxy.static_test;

import com.github.dge1992.proxy.CalculateServiceImpl;
import com.github.dge1992.proxy.static_test.StaticProxy;

public class TestStaticProxy {

    /**
     * 因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护
     * @param args
     */
    public static void main(String[] args) {
        CalculateServiceImpl calculateService = new CalculateServiceImpl();
        StaticProxy staticProxy = new StaticProxy(calculateService);
        staticProxy.add(1, 2);
        staticProxy.subtract(5, 2);
    }
}
