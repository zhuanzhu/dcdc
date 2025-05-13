package com.egeo.components.order.service.read;

import com.egeo.components.order.dto.QmOrderDTO;

import java.util.List;

public interface QmOrderReadService {

    QmOrderDTO findByOutTradeNo(String outTradeNo);

    QmOrderDTO findByOrderCode(String orderCode);

    QmOrderDTO findBySoId(Long soId);


    List<QmOrderDTO> findWaitSyncOrder(Integer synPayMaxTimes,Integer pageSize);

}
