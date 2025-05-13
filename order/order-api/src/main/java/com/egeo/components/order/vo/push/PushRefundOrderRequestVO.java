package com.egeo.components.order.vo.push;

import java.io.Serializable;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class PushRefundOrderRequestVO implements Serializable {

    /**
     * 订单号
     **/
     private String orderCode;

    /**
     * 订单备注
     **/
     private String remark;

     /**
      *  退款金额
      **/
     private List<OrderRefundDetailVO> refundDetail;

    /**
     *  退款子订单信息
     **/
     private List<RefunChildOrderVO> childOrder;


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OrderRefundDetailVO> getRefundDetail() {
        return refundDetail;
    }

    public void setRefundDetail(List<OrderRefundDetailVO> refundDetail) {
        this.refundDetail = refundDetail;
    }

    public List<RefunChildOrderVO> getChildOrder() {
        return childOrder;
    }

    public void setChildOrder(List<RefunChildOrderVO> childOrder) {
        this.childOrder = childOrder;
    }
}
