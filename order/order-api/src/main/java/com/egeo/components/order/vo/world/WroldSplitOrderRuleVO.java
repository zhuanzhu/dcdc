package com.egeo.components.order.vo.world;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WroldSplitOrderRuleVO implements Serializable {

    private Integer goodsType;

    private String store_id;

    private String store_code;

    private Boolean store_isCombineOrders;

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public Boolean getStore_isCombineOrders() {
        return store_isCombineOrders;
    }

    public void setStore_isCombineOrders(Boolean store_isCombineOrders) {
        this.store_isCombineOrders = store_isCombineOrders;
    }
}
