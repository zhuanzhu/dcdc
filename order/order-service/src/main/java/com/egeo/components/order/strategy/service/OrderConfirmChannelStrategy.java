package com.egeo.components.order.strategy.service;

import com.egeo.components.order.dto.OrderResult;
import com.egeo.components.order.strategy.vo.OrderConfirmReqVO;

/**
 * @Description 按渠道确认
 * @Author lsl
 * @Date 2024/12/1 12:51
 * @Version V1.0
 **/
public interface OrderConfirmChannelStrategy {

    public String getChannelCode();

    public OrderResult checkBefore(OrderConfirmReqVO reqVO);

    public OrderResult buyPrductByRightNow(OrderConfirmReqVO reqVO);

    public OrderResult buyProductByCart(OrderConfirmReqVO reqVO);
}
