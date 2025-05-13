package com.egeo.components.order.strategy.service;

import com.egeo.components.order.strategy.vo.CreateOrderReqVO;
import com.egeo.components.order.strategy.vo.CreateOrderRespVO;
import com.egeo.web.JsonResult;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 13:23
 * @Version V1.0
 **/
public interface OrderCreateStrategy {

    public String getChannelCode();

    public JsonResult<CreateOrderRespVO> getProduct(CreateOrderReqVO createOrderReqVO);

    public JsonResult<CreateOrderRespVO> addItemsAndLimitRules(CreateOrderReqVO createOrderReqVO);
}
