package com.elliot.ordercenter.service.impl;

import cn.hutool.json.JSONUtil;
import com.elliot.elliotcommons.domian.external.FeeItemBO;
import com.elliot.ordercenter.model.Order;
import com.elliot.ordercenter.service.OrderService;
import com.elliot.ordercenter.service.external.SettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private SettlementService settlementService;

    @Override
    public List<Order> getOrderListById() {
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

    @Override
    public String getOrderListTimeOut() throws InterruptedException {
        return settlementService.queryTimeOutByOrderId("123");
    }
}
