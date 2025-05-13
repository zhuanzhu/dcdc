package com.egeo.components.product.dto.world.goodchild;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class WorldCommodityCustomerInfoDTO implements Serializable {

    /**
     * 	String	是	是否需要申报支付单（1-需要，0-不需要）
     **/
    private String payDeclare;
    /**
     * 	String	是	不需要
     **/
    private String payDeclareEnumDescription;
    /**
     * 	String是	是否需要申报订单（1-需要，0-不需要）
     **/
    private String orderDeclare	;
    /**
     * 	String	是	不需要
     **/
    private String orderDeclareEnumDescription;
    /**
     * 	String	是	电商企业平台名称
     **/
    private String enterpriseName;
    /**
     * 	String	是	电商企业平台编码
     **/
    private String enterpriseCode;
    /**
     * 	String	是	申报订单商品SKU编码
     **/
    private String declareSKUCode;
    /**
     * 	String	是	申报订单商品名称
     **/
    private String declareCommodityName;
    /**
     * 	String	是	申报订单功能
     **/
    private String function;
    /**
     * 	String	是	申报订单国际条码
     **/
    private String declareBarCode;
    /**
     * 	String	是	申报订单单位编码
     **/
    private String customMeasurementUnitCode;
    /**
     * 	String	是	申报订单单位
     **/
    private String customMeasurementUnit;
    /**
     * 	String	是	申报订单产地编码
     **/
    private String customCountryCode;
    /**
     * 	String	是	申报订单产地
     **/
    private String customCountry;
    /**
     * 	String	是	申报订单币制编码
     **/
    private String customCurrencyCode;
    /**
     * 	String	是	申报订单币制
     **/
    private String customCurrency;
    /**
     * String	是	申报订单商品规格
     **/
    private String customSpecifications	;

    public String getPayDeclare() {
        return payDeclare;
    }

    public void setPayDeclare(String payDeclare) {
        this.payDeclare = payDeclare;
    }

    public String getPayDeclareEnumDescription() {
        return payDeclareEnumDescription;
    }

    public void setPayDeclareEnumDescription(String payDeclareEnumDescription) {
        this.payDeclareEnumDescription = payDeclareEnumDescription;
    }

    public String getOrderDeclare() {
        return orderDeclare;
    }

    public void setOrderDeclare(String orderDeclare) {
        this.orderDeclare = orderDeclare;
    }

    public String getOrderDeclareEnumDescription() {
        return orderDeclareEnumDescription;
    }

    public void setOrderDeclareEnumDescription(String orderDeclareEnumDescription) {
        this.orderDeclareEnumDescription = orderDeclareEnumDescription;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getDeclareSKUCode() {
        return declareSKUCode;
    }

    public void setDeclareSKUCode(String declareSKUCode) {
        this.declareSKUCode = declareSKUCode;
    }

    public String getDeclareCommodityName() {
        return declareCommodityName;
    }

    public void setDeclareCommodityName(String declareCommodityName) {
        this.declareCommodityName = declareCommodityName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDeclareBarCode() {
        return declareBarCode;
    }

    public void setDeclareBarCode(String declareBarCode) {
        this.declareBarCode = declareBarCode;
    }

    public String getCustomMeasurementUnitCode() {
        return customMeasurementUnitCode;
    }

    public void setCustomMeasurementUnitCode(String customMeasurementUnitCode) {
        this.customMeasurementUnitCode = customMeasurementUnitCode;
    }

    public String getCustomMeasurementUnit() {
        return customMeasurementUnit;
    }

    public void setCustomMeasurementUnit(String customMeasurementUnit) {
        this.customMeasurementUnit = customMeasurementUnit;
    }

    public String getCustomCountryCode() {
        return customCountryCode;
    }

    public void setCustomCountryCode(String customCountryCode) {
        this.customCountryCode = customCountryCode;
    }

    public String getCustomCountry() {
        return customCountry;
    }

    public void setCustomCountry(String customCountry) {
        this.customCountry = customCountry;
    }

    public String getCustomCurrencyCode() {
        return customCurrencyCode;
    }

    public void setCustomCurrencyCode(String customCurrencyCode) {
        this.customCurrencyCode = customCurrencyCode;
    }

    public String getCustomCurrency() {
        return customCurrency;
    }

    public void setCustomCurrency(String customCurrency) {
        this.customCurrency = customCurrency;
    }

    public String getCustomSpecifications() {
        return customSpecifications;
    }

    public void setCustomSpecifications(String customSpecifications) {
        this.customSpecifications = customSpecifications;
    }
}
