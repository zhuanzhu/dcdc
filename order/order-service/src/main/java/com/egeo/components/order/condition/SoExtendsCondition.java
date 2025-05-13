package com.egeo.components.order.condition;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/10/24 16:29
 * @Version V1.0
 **/
public class SoExtendsCondition  {

    private Long id ;

    private String orderCode;

    private String ifChildOrder;

    private Integer orderStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getIfChildOrder() {
        return ifChildOrder;
    }

    public void setIfChildOrder(String ifChildOrder) {
        this.ifChildOrder = ifChildOrder;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
