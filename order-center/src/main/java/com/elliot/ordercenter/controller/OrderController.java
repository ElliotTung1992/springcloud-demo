package com.elliot.ordercenter.controller;

import com.elliot.ordercenter.model.Order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/order")
@RestController
public class OrderController {

    @RequestMapping("/getOrderList")
    public List<Order> getgetOrderListById(){
        List<Order> list = new ArrayList<>();
        Order order = new Order();
        order.setId(UUID.randomUUID().toString().replace("-", ""));
        order.setOrderName("Apple");
        order.setPrice(new BigDecimal(2200));
        list.add(order);
        return list;
    }
}
