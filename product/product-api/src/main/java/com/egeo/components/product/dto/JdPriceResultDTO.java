package com.egeo.components.product.dto;

import java.math.BigDecimal;

public class JdPriceResultDTO {
	private Long  skuId	;  
	private String  jdPrice;  
	private String  price;  
	private String  marketPrice	;  
	private String  tax	;  
	private String  taxPrice	;  
	private String  nakedPrice	;
	public Long getSkuId() {
		return skuId;
	}
	public String getSkuIdStr() {
		return String.valueOf(skuId);
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public String getJdPrice() {
		return jdPrice;
	}
	public BigDecimal  getJdPriceBigDecimal() {
		return new BigDecimal(jdPrice);
	}
	public void setJdPrice(String jdPrice) {
		this.jdPrice = jdPrice;
	}
	public String getPrice() {
		return price;
	}
	public BigDecimal  getPriceBigDecimal() {
		return new BigDecimal(price);
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(String taxPrice) {
		this.taxPrice = taxPrice;
	}
	public String getNakedPrice() {
		return nakedPrice;
	}
	public void setNakedPrice(String nakedPrice) {
		this.nakedPrice = nakedPrice;
	}  

}
