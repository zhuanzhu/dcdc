package com.egeo.components.order.vo.jd;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 0.0 on 2019/3/28.
 */
public class JdOrderSubmit {
    private Long jdOrderId;
    private BigDecimal orderPrice;
    private BigDecimal orderNakedPrice;
    private BigDecimal orderTaxPrice;

    public Long getJdOrderId() {
        return jdOrderId;
    }

    public void setJdOrderId(Long jdOrderId) {
        this.jdOrderId = jdOrderId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderNakedPrice() {
        return orderNakedPrice;
    }

    public void setOrderNakedPrice(BigDecimal orderNakedPrice) {
        this.orderNakedPrice = orderNakedPrice;
    }

    public BigDecimal getOrderTaxPrice() {
        return orderTaxPrice;
    }

    public void setOrderTaxPrice(BigDecimal orderTaxPrice) {
        this.orderTaxPrice = orderTaxPrice;
    }

    public List<JdBizSku> getSku() {
        return sku;
    }

    public void setSku(List<JdBizSku> sku) {
        this.sku = sku;
    }

    private List<JdBizSku> sku;
}
