package com.egeo.components.order.vo.cake;

/**
 * @Description todo
 * @Author lsl
 * @Date 2024/11/11 19:07
 * @Version V1.0
 **/
public class ChannelSkuInfoVO {

    private String skuId;
    private Integer num;

    private String productId;

    private Boolean store_isCombineOrders;

    private String goodType;

    private String distribution_rule_id;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Boolean getStore_isCombineOrders() {
        return store_isCombineOrders;
    }

    public void setStore_isCombineOrders(Boolean store_isCombineOrders) {
        this.store_isCombineOrders = store_isCombineOrders;
    }

    public String getGoodType() {
        return goodType;
    }

    public void setGoodType(String goodType) {
        this.goodType = goodType;
    }

    public String getDistribution_rule_id() {
        return distribution_rule_id;
    }

    public void setDistribution_rule_id(String distribution_rule_id) {
        this.distribution_rule_id = distribution_rule_id;
    }
}
