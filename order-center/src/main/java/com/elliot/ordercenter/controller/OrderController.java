package com.elliot.ordercenter.controller;

import cn.hutool.json.JSONUtil;
import com.elliot.elliotcommons.domian.external.FeeItemBO;
import com.elliot.ordercenter.model.Order;
import com.elliot.ordercenter.service.external.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private SettlementService settlementService;

    @RequestMapping("/getOrderList")
    public List<Order> getgetOrderListById(){
        List<Order> list = new ArrayList<>();
        Order order = new Order();
        order.setId(UUID.randomUUID().toString().replace("-", ""));
        order.setOrderName("Apple");
        order.setPrice(new BigDecimal(2200));
        list.add(order);

        List<FeeItemBO> feeItemBOS = settlementService.queryByOrderId("11");
        System.out.println(JSONUtil.toJsonStr(feeItemBOS));

        return list;
    }
}
