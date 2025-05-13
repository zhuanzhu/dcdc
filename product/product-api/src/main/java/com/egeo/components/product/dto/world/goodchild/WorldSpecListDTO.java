package com.egeo.components.product.dto.world.goodchild;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldSpecListDTO implements Serializable {
    /**
     * 	String	是	规格件装
     **/
    private String spec_num;
    /**
     * 	String	是	规格件装名称
     **/
    private String specName;
    /**
     * 	String	是	有效期
     **/
    private String expired_date;
    /**
     * 	String	是	生成日期
     **/
    private String make_date;
    /**
     * 	String	是	建议零售价
     **/
    private String price;
    /**
     * 	String	是	控价
     **/
    private String priceControl;
    /**
     * 	String	是	会员价
     **/
    private String priceVip;
    /**
     * 	String	是	结算价
     **/
    private String priceSettlement;
    /**
     * 	String	是	市场价
     **/
    private String priceMarket;
    /**
     * 	String	是	是否是最小价格元素
     **/
    private String isMinStockSpecificationItem;

    private String defaultStock;

    public String getSpec_num() {
        return spec_num;
    }

    public void setSpec_num(String spec_num) {
        this.spec_num = spec_num;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(String expired_date) {
        this.expired_date = expired_date;
    }

    public String getMake_date() {
        return make_date;
    }

    public void setMake_date(String make_date) {
        this.make_date = make_date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceControl() {
        return priceControl;
    }

    public void setPriceControl(String priceControl) {
        this.priceControl = priceControl;
    }

    public String getPriceVip() {
        return priceVip;
    }

    public void setPriceVip(String priceVip) {
        this.priceVip = priceVip;
    }

    public String getPriceSettlement() {
        return priceSettlement;
    }

    public void setPriceSettlement(String priceSettlement) {
        this.priceSettlement = priceSettlement;
    }

    public String getPriceMarket() {
        return priceMarket;
    }

    public void setPriceMarket(String priceMarket) {
        this.priceMarket = priceMarket;
    }

    public String getIsMinStockSpecificationItem() {
        return isMinStockSpecificationItem;
    }

    public void setIsMinStockSpecificationItem(String isMinStockSpecificationItem) {
        this.isMinStockSpecificationItem = isMinStockSpecificationItem;
    }

    public String getDefaultStock() {
        return defaultStock;
    }

    public void setDefaultStock(String defaultStock) {
        this.defaultStock = defaultStock;
    }
}
