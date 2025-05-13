package com.egeo.components.order.manage.read;

import com.egeo.components.order.po.QmOrderPO;

import java.util.List;

public interface QmOrderReadManage {

    QmOrderPO findByOutTradeNo(String outTradeNo);

    QmOrderPO findByOrderCode(String orderCode);

    QmOrderPO findBySoId(Long soId);

    List<QmOrderPO> findWaitSyncOrder(Integer synPayMaxTimes,Integer pageSize);

}
