package com.egeo.components.order.strategy.service;

import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.strategy.vo.OrderConfirmChannelDeliveryReqVO;

import java.util.Map;

/**
 * @Description 按订单确认-按商户获取运费
 * @Author lsl
 * @Date 2024/12/1 12:53
 * @Version V1.0
 **/
public interface OrderConfirmMerchantDeliveryStrategy {

    public String getChannelCode();

    public Map<String,Object> getDelivery(OrderConfirmChannelDeliveryReqVO reqVO);
}
