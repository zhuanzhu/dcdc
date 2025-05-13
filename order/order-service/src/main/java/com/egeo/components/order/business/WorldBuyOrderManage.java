package com.egeo.components.order.business;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.vo.world.WorldPayReqVO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface WorldBuyOrderManage {

    JSONObject createOrder(JSONObject jsonObject);
    JSONObject orderPay(WorldPayReqVO vo);
}
