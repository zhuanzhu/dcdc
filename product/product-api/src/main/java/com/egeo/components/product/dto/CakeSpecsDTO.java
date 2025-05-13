package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description 蛋糕叔叔规格
 * @Author lsl
 * @Date 2024/4/28 17:24
 * @Version V1.0
 **/
public class CakeSpecsDTO implements Serializable {

    private String spec_id;

    private String name;

    private String price;

    private String market_price;

    private String clearing_price;

    private String promotion_id;

    private String shop_discount;

    public String getSpec_id() {
        return spec_id;
    }

    public void setSpec_id(String spec_id) {
        this.spec_id = spec_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getClearing_price() {
        return clearing_price;
    }

    public void setClearing_price(String clearing_price) {
        this.clearing_price = clearing_price;
    }

    public String getPromotion_id() {
        return promotion_id;
    }

    public void setPromotion_id(String promotion_id) {
        this.promotion_id = promotion_id;
    }

    public String getShop_discount() {
        return shop_discount;
    }

    public void setShop_discount(String shop_discount) {
        this.shop_discount = shop_discount;
    }
}
