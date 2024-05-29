package com.elliot.settlementcenter.controller;

import com.elliot.elliotcommons.domian.external.FeeItemBO;
import com.elliot.elliotcommons.domian.external.SettlementBO;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequestMapping("/settlement")
@RestController
public class SettlementController {

    @GetMapping("/queryByOrderId")
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

    @GetMapping("/queryTimeOutByOrderId")
    public String queryTimeOutByOrderId(@RequestParam("orderId") String orderId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return "haha";
    }

    @PostMapping("/insertSettlement")
    public void insertSettlement(@RequestBody SettlementBO settlementBO){
        System.out.println(settlementBO);
    }
}
