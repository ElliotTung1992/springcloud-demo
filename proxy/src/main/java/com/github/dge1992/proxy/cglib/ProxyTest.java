package com.github.dge1992.proxy.cglib;

import com.github.dge1992.proxy.CalculateServiceImpl;

public class ProxyTest {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new CalculateServiceImpl());
        CalculateServiceImpl proxyInstance = (CalculateServiceImpl) proxyFactory.getProxyInstance();
        proxyInstance.add(2, 3);
        proxyInstance.subtract(7, 2);
    }
}
