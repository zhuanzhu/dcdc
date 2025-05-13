package com.egeo.components.order.strategy.vo;

import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.dto.SoItemDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/5 13:27
 * @Version V1.0
 **/
public class CreateOrderRespVO implements Serializable {

    private Integer type;

    private Integer sourceProduct;

    private Integer source;

    /**商品对象**/
    private Object productObject;

    /**对应渠道对应的商品总金额**/
    private  Double orderAmount;

    /**对应渠道对应的商品列表**/
    private List<SoItemDTO> soItems;

    // 组织限购规则记录集合
    List<LimitRuleRecordDTO> limitRuleRecordList;

    private BigDecimal limitFuBiPayAmount;

    private Double orderPayByCash;

    public Integer getSourceProduct() {
        return sourceProduct;
    }

    public void setSourceProduct(Integer sourceProduct) {
        this.sourceProduct = sourceProduct;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Object getProductObject() {
        return productObject;
    }

    public void setProductObject(Object productObject) {
        this.productObject = productObject;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public List<SoItemDTO> getSoItems() {
        return soItems;
    }

    public void setSoItems(List<SoItemDTO> soItems) {
        this.soItems = soItems;
    }

    public List<LimitRuleRecordDTO> getLimitRuleRecordList() {
        return limitRuleRecordList;
    }

    public void setLimitRuleRecordList(List<LimitRuleRecordDTO> limitRuleRecordList) {
        this.limitRuleRecordList = limitRuleRecordList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getLimitFuBiPayAmount() {
        return limitFuBiPayAmount;
    }

    public void setLimitFuBiPayAmount(BigDecimal limitFuBiPayAmount) {
        this.limitFuBiPayAmount = limitFuBiPayAmount;
    }

    public Double getOrderPayByCash() {
        return orderPayByCash;
    }

    public void setOrderPayByCash(Double orderPayByCash) {
        this.orderPayByCash = orderPayByCash;
    }
}
