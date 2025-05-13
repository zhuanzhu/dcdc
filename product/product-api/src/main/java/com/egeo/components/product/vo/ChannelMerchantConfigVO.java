package com.egeo.components.product.vo;

import java.io.Serializable;

public class ChannelMerchantConfigVO implements Serializable {
	private Integer id;
	private Integer merchantId;
	private Integer type;
	private Integer priceAddtion;
	private Integer priceAddtionMax;
	private Integer priceAddtionMin;
	private Integer grossMarginMax;
	private Integer grossMarginMin;
	private String plateformAddtion;
	private String channelCategorys;
	private String channelPriceMax;
	private String channelPriceMin;
	private Integer radio;
	private String channelCode;

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

	public String getChannelCategorys() {
		return channelCategorys;
	}

	public void setChannelCategorys(String channelCategorys) {
		this.channelCategorys = channelCategorys;
	}

	public String getChannelPriceMax() {
		return channelPriceMax;
	}

	public void setChannelPriceMax(String channelPriceMax) {
		this.channelPriceMax = channelPriceMax;
	}

	public String getChannelPriceMin() {
		return channelPriceMin;
	}

	public void setChannelPriceMin(String channelPriceMin) {
		this.channelPriceMin = channelPriceMin;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
}
