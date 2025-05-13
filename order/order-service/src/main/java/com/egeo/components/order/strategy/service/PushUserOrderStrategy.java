package com.egeo.components.order.strategy.service;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.strategy.vo.PushUserOrderReqVO;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/30 14:39
 * @Version V1.0
 **/
public interface PushUserOrderStrategy {

    public String getChannelCode();

    public JSONObject pushOrderInfo(PushUserOrderReqVO pushUserOrderReqVO);
}
