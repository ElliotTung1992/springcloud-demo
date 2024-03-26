package com.elliot.ordercenter.service.external;

import com.elliot.elliotcommons.domian.external.FeeItemBO;
import com.elliot.ordercenter.service.external.fallback.SettlementFallbackService;
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

    /**
     * 测试异常 - 超时
     * @param orderId orderId
     * @return List<FeeItemBO>
     */
    @GetMapping("/settlement/queryTimeOutByOrderId")
    String queryTimeOutByOrderId(@RequestParam("orderId") String orderId);
}
