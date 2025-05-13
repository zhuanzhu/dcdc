package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Version V1.0
 **/
public class StandardUnitCardBaseSuDetailReqVO implements Serializable {

    private String productId;

    private Integer source;

    private String thirdSkuId;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getThirdSkuId() {
        return thirdSkuId;
    }

    public void setThirdSkuId(String thirdSkuId) {
        this.thirdSkuId = thirdSkuId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
