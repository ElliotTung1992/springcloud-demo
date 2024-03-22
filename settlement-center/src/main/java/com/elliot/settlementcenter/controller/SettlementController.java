package com.elliot.settlementcenter.controller;

import com.elliot.elliotcommons.domian.external.FeeItemBO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
}
