package com.egeo.components.product.vo;

import java.util.Date;

public class JdMerchantConfigVO {
	private Integer id;
	private Integer merchantId;
	private Integer type;
	private Integer priceAddtion;
	private Integer priceAddtionMax;
	private Integer priceAddtionMin;
	private Integer grossMarginMax;
	private Integer grossMarginMin;
	private String plateformAddtion;
	private String jdCategorys;
	private String jdPriceMax;
	private String jdPriceMin;
	private Integer radio;
	
	public String getPlateformAddtion() {
		return plateformAddtion;
	}
	public void setPlateformAddtion(String plateformAddtion) {
		this.plateformAddtion = plateformAddtion;
	}
	public Integer getRadio() {
		return radio;
	}
	public void setRadio(Integer radio) {
		this.radio = radio;
	}
	public String getJdCategorys() {
		return jdCategorys;
	}
	public void setJdCategorys(String jdCategorys) {
		this.jdCategorys = jdCategorys;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPriceAddtion() {
		return priceAddtion;
	}
	public void setPriceAddtion(Integer priceAddtion) {
		this.priceAddtion = priceAddtion;
	}
	public Integer getPriceAddtionMax() {
		return priceAddtionMax;
	}
	public void setPriceAddtionMax(Integer priceAddtionMax) {
		this.priceAddtionMax = priceAddtionMax;
	}
	public Integer getPriceAddtionMin() {
		return priceAddtionMin;
	}
	public void setPriceAddtionMin(Integer priceAddtionMin) {
		this.priceAddtionMin = priceAddtionMin;
	}
	public Integer getGrossMarginMax() {
		return grossMarginMax;
	}
	public void setGrossMarginMax(Integer grossMarginMax) {
		this.grossMarginMax = grossMarginMax;
	}
	public Integer getGrossMarginMin() {
		return grossMarginMin;
	}
	public void setGrossMarginMin(Integer grossMarginMin) {
		this.grossMarginMin = grossMarginMin;
	}
	public String getJd_categorys() {
		return jdCategorys;
	}
	public void setJd_categorys(String jdCategorys) {
		this.jdCategorys = jdCategorys;
	}
	public String getJdPriceMax() {
		return jdPriceMax;
	}
	public void setJdPriceMax(String jdPriceMax) {
		this.jdPriceMax = jdPriceMax;
	}
	public String getJdPriceMin() {
		return jdPriceMin;
	}
	public void setJdPriceMin(String jdPriceMin) {
		this.jdPriceMin = jdPriceMin;
	}
}
