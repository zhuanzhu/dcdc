package com.egeo.components.product.strategy;

import com.egeo.components.product.bean.ReceiveProductBean;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public interface ReceiveProductStrategy {

    /**获取产品code 参照枚举ProductChannelCodeEnum**/
     String getProductCode();

    /**产品接收供应商推送商品通知**/
     Object receiveProduct(ReceiveProductBean bean);
}
