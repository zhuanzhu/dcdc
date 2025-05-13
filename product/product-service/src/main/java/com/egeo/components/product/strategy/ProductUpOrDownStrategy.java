package com.egeo.components.product.strategy;

import com.egeo.components.product.bean.ReceiveProductBean;
import com.egeo.components.product.dto.world.WorldNoticeResponseDTO;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ProductUpOrDownStrategy {

    /**获取产品code 参照枚举ProductChannelCodeEnum**/
    String getProductCode();

    /**处理供应商推送产品状态变更通知**/
    Object receiveProductState(ReceiveProductBean bean);

    public WorldNoticeResponseDTO receiveAllProductState(ReceiveProductBean bean);
}
