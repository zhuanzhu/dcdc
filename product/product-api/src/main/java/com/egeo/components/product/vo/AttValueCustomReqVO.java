package com.egeo.components.product.vo;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/12/17 17:24
 * @Version V1.0
 **/
public class AttValueCustomReqVO implements Serializable {

    private Long standardProductUnitId;

    private String attrName;

    public Long getStandardProductUnitId() {
        return standardProductUnitId;
    }

    public void setStandardProductUnitId(Long standardProductUnitId) {
        this.standardProductUnitId = standardProductUnitId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
}
