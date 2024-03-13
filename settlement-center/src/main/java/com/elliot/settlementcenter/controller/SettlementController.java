package com.elliot.settlementcenter.controller;

import com.elliot.settlementcenter.model.FeeItemDTO;
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
    public List<FeeItemDTO> queryByOrderId(@RequestParam("orderId") String orderId){
        List<FeeItemDTO> list = new ArrayList<>();
        FeeItemDTO feeItemDTO = new FeeItemDTO();
        feeItemDTO.setOrderId(orderId);
        feeItemDTO.setFeeItemName("apple");
        feeItemDTO.setPrice(new BigDecimal(1000));
        feeItemDTO.setQuantity(new BigDecimal(1));
        list.add(feeItemDTO);
        return list;
    }
}
