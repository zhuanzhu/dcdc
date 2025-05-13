package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/8/30 10:56
 * @Version V1.0
 **/
public class SkuInfoAttVO implements Serializable {

    private String attValueId;

    private String attValue;

    private Long realStockNum;

    private Integer status;

    private BigDecimal salePrice;

    public String getAttValueId() {
        return attValueId;
    }

    public void setAttValueId(String attValueId) {
        this.attValueId = attValueId;
    }

    public String getAttValue() {
        return attValue;
    }

    public void setAttValue(String attValue) {
        this.attValue = attValue;
    }

    public Long getRealStockNum() {
        return realStockNum;
    }

    public void setRealStockNum(Long realStockNum) {
        this.realStockNum = realStockNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
}
