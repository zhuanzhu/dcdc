package com.egeo.components.order.business;

import com.egeo.components.order.dto.CancelAndRefundOrderExtendsWithTxDTO;
import com.egeo.components.order.dto.CancelAndRefundOrderWithTxDTO;

import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/22 16:05
 * @Version V1.0
 **/
public interface SoRefundNewManage {

    Map<String,Object> productRefundOrderWithTx(CancelAndRefundOrderExtendsWithTxDTO dto);
}
