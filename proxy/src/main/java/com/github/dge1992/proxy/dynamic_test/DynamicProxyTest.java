package com.github.dge1992.proxy.dynamic_test;

import com.github.dge1992.proxy.CalculateServiceImpl;
import com.github.dge1992.proxy.ICalculateService;

public class DynamicProxyTest {

    public static void main(String[] args) {
        /**
         * 代理对象不需要实现接口,但是目标对象一定要实现接口,否则不能用动态代理
         */
        CalculateServiceImpl calculateService = new CalculateServiceImpl();
        System.out.println(calculateService.getClass());

        DynamicProxy dynamicProxy = new DynamicProxy(calculateService);
        // 只能代理接口
//        CalculateServiceImpl service = (CalculateServiceImpl) dynamicProxy.getProxyInstance();
        ICalculateService proxyInstance = (ICalculateService) dynamicProxy.getProxyInstance();
        System.out.println(proxyInstance.getClass());

        proxyInstance.add(1, 2);
        proxyInstance.subtract(6, 3);
    }
}
