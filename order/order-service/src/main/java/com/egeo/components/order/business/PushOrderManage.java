package com.egeo.components.order.business;

import com.alibaba.fastjson.JSONObject;
import com.egeo.components.order.dto.CancelAndRefundOrderExtendsWithTxDTO;
import com.egeo.components.order.vo.RePushRefundOrderVO;

import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface PushOrderManage {

    public JSONObject pushOrderInfo(Long soId,Long enterpriseId,String channelCode);

    public JSONObject pushRefundOrder(Long soId,Long enterpriseId,String channelCode);

    public JSONObject pushRefundOrder(CancelAndRefundOrderExtendsWithTxDTO dto);

    public JSONObject pushOrderToThird(Long soId,String exceptReason);

    public void pushChildOrderToThird(List<Long> needPushChildIds,String exceptReason);

    public JSONObject rePushRefundOrderByItemAndRefund(RePushRefundOrderVO vo);
}
