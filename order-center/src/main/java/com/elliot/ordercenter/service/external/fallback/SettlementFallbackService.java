package com.elliot.ordercenter.service.external.fallback;


import com.elliot.elliotcommons.domian.external.FeeItemBO;
import com.elliot.ordercenter.service.external.SettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SettlementFallbackService implements SettlementService {

    @Override
    public List<FeeItemBO> queryByOrderId(String orderId) {
        return null;
    }

    @Override
    public List<FeeItemBO> queryTimeOutByOrderId(String orderId) {
        log.error("查询SettlementService:queryTimeOutByOrderId请求超时了");
        return null;
    }
}
