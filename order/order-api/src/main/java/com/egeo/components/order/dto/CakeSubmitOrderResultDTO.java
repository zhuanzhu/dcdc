package com.egeo.components.order.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/9 15:54
 * @Version V1.0
 **/
public class CakeSubmitOrderResultDTO implements Serializable {

    /**
     * 	string	164589	购物车id
     **/
    private String cart_id;

    /**
     * 		string	1901435326431	购物车单号
     **/
    private String cart_order_no;

    /**
     * 	string	1901435325798	订单号
     **/
    private String order_no;

    /**
     * 	string	18	订单运费金额
     **/
    private String ship_amount;

    /**
     * 	string	0	支付状态：0-待支付，1-已支付
     **/
    private String pay_status;

    /**
     * 	订单信息
     **/
    private CakeSubmitOrderDetailResultDTO order;

    /**
     * 	订单结算价对象
     **/
    private CakeSubmitOrderClearingInfo clearing_info;

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getCart_order_no() {
        return cart_order_no;
    }

    public void setCart_order_no(String cart_order_no) {
        this.cart_order_no = cart_order_no;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getShip_amount() {
        return ship_amount;
    }

    public void setShip_amount(String ship_amount) {
        this.ship_amount = ship_amount;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public CakeSubmitOrderDetailResultDTO getOrder() {
        return order;
    }

    public void setOrder(CakeSubmitOrderDetailResultDTO order) {
        this.order = order;
    }

    public CakeSubmitOrderClearingInfo getClearing_info() {
        return clearing_info;
    }

    public void setClearing_info(CakeSubmitOrderClearingInfo clearing_info) {
        this.clearing_info = clearing_info;
    }
}
