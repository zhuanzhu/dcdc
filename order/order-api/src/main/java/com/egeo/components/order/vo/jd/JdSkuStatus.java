package com.egeo.components.order.vo.jd;

/**
 * Created by 0.0 on 2019/3/26.
 */
public class JdSkuStatus {
    private Integer state;
    private Long sku;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }
}
