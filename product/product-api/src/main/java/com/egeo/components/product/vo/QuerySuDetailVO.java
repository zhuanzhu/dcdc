package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author PAUL
 * @date 2018-09-03 09:20:45
 */
public class QuerySuDetailVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *商品名称
	 */
	private String name;
	/**
	 * merchant_product编号
	 */
	private String merchantProductSerialNumber;	
	/**
	 * 销售价格-起始值
	 */
	private BigDecimal beginSalePrice;
	/**
	 * 销售价格-结束值
	 */
	private BigDecimal endSalePrice;
	/**
	 * 促销价格-起始值
	 */
	private BigDecimal beginPromotionPrice;
	/**
	 * 促销价格-结束值
	 */
	private BigDecimal endPromotionPrice;
	/**
	 * 商品状态（1、待上架 2、审核中 3、已上架 4、已下架 5、审核未通过）
	 */
	private Integer status;
	/**
	 * 是否可见：默认0是;1否
	 */
	private Integer isVisible;
	/**
	 * 平台id
	 */
	private Long platformId;
	/**
	 * 运营方ID
	 */
	private Long merchantId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchantProductSerialNumber() {
        return merchantProductSerialNumber;
    }

    public void setMerchantProductSerialNumber(String merchantProductSerialNumber) {
        this.merchantProductSerialNumber = merchantProductSerialNumber;
    }

    public BigDecimal getBeginSalePrice() {
        return beginSalePrice;
    }

    public void setBeginSalePrice(BigDecimal beginSalePrice) {
        this.beginSalePrice = beginSalePrice;
    }

    public BigDecimal getEndSalePrice() {
        return endSalePrice;
    }

    public void setEndSalePrice(BigDecimal endSalePrice) {
        this.endSalePrice = endSalePrice;
    }

    public BigDecimal getBeginPromotionPrice() {
        return beginPromotionPrice;
    }

    public void setBeginPromotionPrice(BigDecimal beginPromotionPrice) {
        this.beginPromotionPrice = beginPromotionPrice;
    }

    public BigDecimal getEndPromotionPrice() {
        return endPromotionPrice;
    }

    public void setEndPromotionPrice(BigDecimal endPromotionPrice) {
        this.endPromotionPrice = endPromotionPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Long getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
    
}
	