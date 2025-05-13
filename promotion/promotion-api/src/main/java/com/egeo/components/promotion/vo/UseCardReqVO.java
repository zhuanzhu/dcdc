package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Description todo
 * @Author lsl
 * @Date 2025/3/27 17:38
 * @Version V1.0
 **/
public class UseCardReqVO implements Serializable {

    private Long orderId;

    private String orderCode;

    private Map<Long, BigDecimal> map;

    List<UseBuyCardItemDetailReqVO> useDetails;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Map<Long, BigDecimal> getMap() {
        return map;
    }

    public void setMap(Map<Long, BigDecimal> map) {
        this.map = map;
    }

    public List<UseBuyCardItemDetailReqVO> getUseDetails() {
        return useDetails;
    }

    public void setUseDetails(List<UseBuyCardItemDetailReqVO> useDetails) {
        this.useDetails = useDetails;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
