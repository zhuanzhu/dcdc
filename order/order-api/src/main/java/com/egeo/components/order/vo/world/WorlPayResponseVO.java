package com.egeo.components.order.vo.world;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorlPayResponseVO implements Serializable {

    private WorldPayOrderItemResponseVO orderItem;

    private String sendGoodsPoints;

    public WorldPayOrderItemResponseVO getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(WorldPayOrderItemResponseVO orderItem) {
        this.orderItem = orderItem;
    }

    public String getSendGoodsPoints() {
        return sendGoodsPoints;
    }

    public void setSendGoodsPoints(String sendGoodsPoints) {
        this.sendGoodsPoints = sendGoodsPoints;
    }
}
