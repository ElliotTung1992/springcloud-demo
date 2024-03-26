package com.elliot.ordercenter.controller;

import com.elliot.ordercenter.model.Order;
import com.elliot.ordercenter.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/getOrderList")
    public List<Order> getOrderListById(@RequestHeader(value = "company", required = false)String company){
        log.info("getOrderListById:company:{}", company);
        List<Order> list = orderService.getOrderListById();
        return list;
    }

    @RequestMapping("/getOrderListTimeOut")
    public String getOrderListTimeOut() throws InterruptedException {
        return orderService.getOrderListTimeOut();
    }

}
