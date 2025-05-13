package com.egeo.components.order.vo;

import com.egeo.components.order.dto.SoItemDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 退款重推参数模型
 * @Author lsl
 * @Version V1.0
 **/
public class RePushRefundOrderVO implements Serializable {

    private Long refundId;

    private  List<SoItemDTO> items;

    private String childCode;

    private String orderCode;

    private String reason;

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public List<SoItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SoItemDTO> items) {
        this.items = items;
    }

    public String getChildCode() {
        return childCode;
    }

    public void setChildCode(String childCode) {
        this.childCode = childCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
