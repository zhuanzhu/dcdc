package com.egeo.components.product.dto.world.goodchild;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldCommissionLevelListDTO implements Serializable {

    /**
     * 	String	是	等级
     **/
    private String level;
    /**
     * 	String	是	等级名称
     **/
    private String level_name;
    /**
     * 	String	是	总部分佣比例
     **/
    private String master_commission_scale;
    /**
     * 	String	是	渠道分佣比例
     **/
    private String shop_commission_scale;
    /**
     * 	String	是	店铺分佣比例
     **/
    private String store_commission_scale;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getMaster_commission_scale() {
        return master_commission_scale;
    }

    public void setMaster_commission_scale(String master_commission_scale) {
        this.master_commission_scale = master_commission_scale;
    }

    public String getShop_commission_scale() {
        return shop_commission_scale;
    }

    public void setShop_commission_scale(String shop_commission_scale) {
        this.shop_commission_scale = shop_commission_scale;
    }

    public String getStore_commission_scale() {
        return store_commission_scale;
    }

    public void setStore_commission_scale(String store_commission_scale) {
        this.store_commission_scale = store_commission_scale;
    }
}
