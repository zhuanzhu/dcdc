package com.egeo.components.product.dto;

import java.io.Serializable;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/5/6 19:07
 * @Version V1.0
 **/
public class CakeSPUIdSearchProductDetailDTO implements Serializable {

    private String spuId;

    private String skuId;

    private String cityName;

    private String cityId;

    private Long enterpriseId;

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
