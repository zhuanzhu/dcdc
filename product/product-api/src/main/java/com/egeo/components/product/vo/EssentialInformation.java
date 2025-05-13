package com.egeo.components.product.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class EssentialInformation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5873916548808817913L;

	private Long merchantProductId;
	
	private String merchantProductName;
	
	private List<String> list;
	
	private String brandName;
	
	/**
	 * 市场价格
	 */
	private BigDecimal marketPrice;		
	
	/**
	 * 销售价格
	 */
	private BigDecimal salePrice;		
	
	/**
	 * 已售基数
	 */
	private Long soldBase;	
	
	/**
	 * 运费说明
	 */
	private String freightExplain;		 
	/**
	 * 发货说明
	 */
	private String shipmentsExplain;	
	
	private List<SellPlatformMerchantProdVO> sellPlatformMerchantProdList;

	public Long getMerchantProductId() {
		return merchantProductId;
	}

	public void setMerchantProductId(Long merchantProductId) {
		this.merchantProductId = merchantProductId;
	}

	public String getMerchantProductName() {
		return merchantProductName;
	}

	public void setMerchantProductName(String merchantProductName) {
		this.merchantProductName = merchantProductName;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Long getSoldBase() {
		return soldBase;
	}

	public void setSoldBase(Long soldBase) {
		this.soldBase = soldBase;
	}

	public String getFreightExplain() {
		return freightExplain;
	}

	public void setFreightExplain(String freightExplain) {
		this.freightExplain = freightExplain;
	}

	public String getShipmentsExplain() {
		return shipmentsExplain;
	}

	public void setShipmentsExplain(String shipmentsExplain) {
		this.shipmentsExplain = shipmentsExplain;
	}

	public List<SellPlatformMerchantProdVO> getSellPlatformMerchantProdList() {
		return sellPlatformMerchantProdList;
	}

	public void setSellPlatformMerchantProdList(List<SellPlatformMerchantProdVO> sellPlatformMerchantProdList) {
		this.sellPlatformMerchantProdList = sellPlatformMerchantProdList;
	}
	
}
