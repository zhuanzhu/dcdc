package com.egeo.components.order.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/9 15:58
 * @Version V1.0
 **/
public class CakeSubmitOrderClearingInfo implements Serializable {

    /**
     * 	string	1192780	规格id
     **/
    private String spec_id;

    /**
     * 	string	108.42	订单结算价
     **/
    private String clearing_price;

    /**
     * 	string	4.17	服务费
     **/
    private String fee_charge;

    /**
     * 		string	104.25	结算低价
     **/
    private String fee_charge_floor;

    public String getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(String spec_id) {
        this.spec_id = spec_id;
    }

    public String getClearing_price() {
        return clearing_price;
    }

    public void setClearing_price(String clearing_price) {
        this.clearing_price = clearing_price;
    }

    public String getFee_charge() {
        return fee_charge;
    }

    public void setFee_charge(String fee_charge) {
        this.fee_charge = fee_charge;
    }

    public String getFee_charge_floor() {
        return fee_charge_floor;
    }

    public void setFee_charge_floor(String fee_charge_floor) {
        this.fee_charge_floor = fee_charge_floor;
    }
}
