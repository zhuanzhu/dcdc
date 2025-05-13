package com.egeo.components.order.dto;

import com.egeo.utils.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 清美子订单商品信息
 */
public class QingMeiChildItemDTO implements Serializable {

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
     * 类目
     */
    private String category;

    /**
     * 购买数量
     */
    @NotBlank(message = "goodsImage不可为空")
    private String goodsImage;

    /**
     * 商品单价
     */
    @NotNull(message = "price不可为空")
    private BigDecimal price;

    /**
     * 商品实付金额
     */
    @NotNull(message = "goodsPayPrice不可为空")
    private BigDecimal goodsPayPrice;

    /**
     * 税率
     */
    @NotNull(message = "taxRate不可为空")
    private BigDecimal taxRate;

    /**
     * 税收分类编码
     */
    @NotBlank(message = "taxCode不可为空")
    private String taxCode;

    /**
     * 开票单位
     */
    @NotBlank(message = "taxUnit不可为空")
    private String taxUnit;

    private String subBizId;

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

    public BigDecimal getGoodsPayPrice() {
        return goodsPayPrice;
    }

    public void setGoodsPayPrice(BigDecimal goodsPayPrice) {
        this.goodsPayPrice = goodsPayPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getTaxUnit() {
        return taxUnit;
    }

    public void setTaxUnit(String taxUnit) {
        this.taxUnit = taxUnit;
    }

    public Long getPuId(){
        return StringUtils.parseLong(this.getSkuCode());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public  String getSubBizId() {
        return subBizId;
    }

    public void setSubBizId(String subBizId) {
        this.subBizId = subBizId;
    }

    public static void main(String[] args) {
        QingMeiChildItemDTO dto=new QingMeiChildItemDTO();
        dto.setSkuCode("9999999911111");
        System.out.println(dto.getPuId());
    }
}
