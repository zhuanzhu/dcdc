package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldBuySubmitOrderResultDTO implements Serializable {

    private List<WorldOrderItemsResponseDTO> orderItems;

    public List<WorldOrderItemsResponseDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<WorldOrderItemsResponseDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
