package com.egeo.components.order.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class QmRefundItemDTO implements Serializable {

    private String subBizId;
    /**
     * 商品编码
     */
    @NotBlank(message = "skuCode不可为空")
    private String skuCode;

    /**
     * 商品名称
     */
    @NotBlank(message = "skuName不可为空")
    private String skuName;

    /**
     * 购买数量
     */
    @NotNull(message = "quantity不可为空")
    private Integer quantity;

    /**
     * 商品图片
     */
    @NotBlank(message = "goodsImage不可为空")
    private String goodsImage;

    /**
     * 商品单价
     */
    @NotNull(message = "price不可为空")
    private BigDecimal price;

    /**
     * 商品实际退款金额
     */
    @NotNull(message = "goodsRefundPrice不可为空")
    private BigDecimal goodsRefundPrice;

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getGoodsRefundPrice() {
        return goodsRefundPrice;
    }

    public void setGoodsRefundPrice(BigDecimal goodsRefundPrice) {
        this.goodsRefundPrice = goodsRefundPrice;
    }

    public String getSubBizId() {
        return subBizId;
    }

    public void setSubBizId(String subBizId) {
        this.subBizId = subBizId;
    }
}
