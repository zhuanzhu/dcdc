package com.egeo.components.product.vo;

/**
 * Created by 0.0 on 2019/3/26.
 */
public class JdSkuSellStatus {
    private Long skuId;
    private String name;
    private Integer saleState;
    private Integer isCanVAT;
    private Integer is7ToReturn;
    private Integer noReasonToReturn;
    private Integer thwa;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSaleState() {
        return saleState;
    }

    public void setSaleState(Integer saleState) {
        this.saleState = saleState;
    }

    public Integer getIsCanVAT() {
        return isCanVAT;
    }

    public void setIsCanVAT(Integer isCanVAT) {
        this.isCanVAT = isCanVAT;
    }

    public Integer getIs7ToReturn() {
        return is7ToReturn;
    }

    public void setIs7ToReturn(Integer is7ToReturn) {
        this.is7ToReturn = is7ToReturn;
    }

    public Integer getNoReasonToReturn() {
        return noReasonToReturn;
    }

    public void setNoReasonToReturn(Integer noReasonToReturn) {
        this.noReasonToReturn = noReasonToReturn;
    }

    public Integer getThwa() {
        return thwa;
    }

    public void setThwa(Integer thwa) {
        this.thwa = thwa;
    }
}
