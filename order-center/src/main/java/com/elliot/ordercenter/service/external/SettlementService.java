package com.elliot.ordercenter.service.external;

import com.elliot.elliotcommons.domian.external.FeeItemBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "settlement-center")
public interface SettlementService {

    /**
     * 根据订单号查询库存信息
     * @param orderId orderId
     * @return Object
     */
    @GetMapping("/settlement/queryByOrderId")
    List<FeeItemBO> queryByOrderId(@RequestParam("orderId") String orderId);
}
