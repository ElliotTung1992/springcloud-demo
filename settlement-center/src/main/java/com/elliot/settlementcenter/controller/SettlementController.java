package com.elliot.settlementcenter.controller;

import com.elliot.elliotcommons.domian.external.FeeItemBO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("/settlement")
@RestController
public class SettlementController {

    @RequestMapping("/queryByOrderId")
    public List<FeeItemBO> queryByOrderId(@RequestParam("orderId") String orderId){
        List<FeeItemBO> list = new ArrayList<>();
        FeeItemBO feeItemBO = new FeeItemBO();
        feeItemBO.setOrderId(orderId);
        feeItemBO.setFeeItemName("apple");
        feeItemBO.setPrice(new BigDecimal(1000));
        feeItemBO.setQuantity(new BigDecimal(1));
        list.add(feeItemBO);
        return list;
    }

    @RequestMapping("/queryTimeOutByOrderId")
    public String queryTimeOutByOrderId(@RequestParam("orderId") String orderId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "haha";
    }

    public String queryTimeOut() {
        return "hehe";
    }
}
