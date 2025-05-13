package com.egeo.components.order.strategy.service;

import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderReqVO;
import com.egeo.components.order.strategy.vo.SyncCreateThirdPartyOrderRespVO;
import com.egeo.web.JsonResult;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/6 13:41
 * @Version V1.0
 **/
public interface SyncCreateThirdPartyOrderStrategy {

    public String getChannelCode();

    public JsonResult<SyncCreateThirdPartyOrderRespVO> syncThirdPartyOrder(SyncCreateThirdPartyOrderReqVO reqVO);
}
