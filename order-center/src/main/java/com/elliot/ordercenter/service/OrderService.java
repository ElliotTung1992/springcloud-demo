package com.elliot.ordercenter.service;

import com.elliot.ordercenter.model.Order;

import java.util.List;

public interface OrderService {

    /**
     * 通过主键查询订单列表
     * @return List<Order>
     */
    List<Order> getOrderListById();

    /**
     * 获取订单列表超时查询
     */
    void getOrderListTimeOut() throws InterruptedException;

}
