package com.egeo.components.order.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/9 15:55
 * @Version V1.0
 **/
public class CakeSubmitOrderDetailResultDTO implements Serializable {

    /**
     * 	string	1331585	订单id
     **/
    private String id;

    /**
     * 	string	1901435325798	订单号
     **/
    private String order_no;

    /**
     * 	string	188	订单支付金额
     **/
    private String final_amount;

    /**
     * 	string	18	订单运费金额
     **/
    private String ship_amount;

    /**
     * 	string	1	订单类型：1-蛋糕/其他，2-面包
     **/
    private String order_type;

    /**
     * 		string	0	支付状态：0-待支付，1-已支付
     **/
    private String pay_status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(String final_amount) {
        this.final_amount = final_amount;
    }

    public String getShip_amount() {
        return ship_amount;
    }

    public void setShip_amount(String ship_amount) {
        this.ship_amount = ship_amount;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }
}
