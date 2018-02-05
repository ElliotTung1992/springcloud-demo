package com.github.dge1992.proxy.static_test;

import com.github.dge1992.proxy.CalculateServiceImpl;
import com.github.dge1992.proxy.ICalculateService;

/**
 * @author  dong
 * @create  2018/2/5 13:51
 * @desc 静态代理
 **/

/**
 * 静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
 */
public class StaticProxy implements ICalculateService {

    private CalculateServiceImpl calculateService;

    public StaticProxy(CalculateServiceImpl calculateService) {
        this.calculateService = calculateService;
    }

    @Override
    public int add(int val1, int val2) {
        System.out.println("参数是:" + val1 + " " + val2);
        int add = calculateService.add(val1, val2);
        System.out.println("结果是:" + add);
        return add;
    }

    @Override
    public int subtract(int val1, int val2) {
        System.out.println("参数是:" + val1 + " " + val2);
        int add = calculateService.subtract(val1, val2);
        System.out.println("结果是:" + add);
        return add;
    }
}
