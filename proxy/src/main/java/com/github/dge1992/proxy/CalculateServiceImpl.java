package com.github.dge1992.proxy;

public class CalculateServiceImpl implements ICalculateService{

    @Override
    public int add(int val1, int val2) {
        return val1 + val2;
    }

    @Override
    public int subtract(int val1, int val2) {
        return val1 - val2;
    }

}
