package com.egeo.components.product.po;

import java.util.Date;

public class ChannelEnterpriseConfigPO {
	private Integer  id        			;
	private Long  enterpriseId        ;
	private Integer  type        		;
	private Integer  priceAddtion      ;
	private Integer  priceAddtionMax  ;
	private Integer  priceAddtionMin  ;
	private Integer  grossMarginMax   ;
	private Integer  grossMarginMin   ;
	private String   channelCategorys		;
	private String   channelPriceMax		;
	private String   channelPriceMin		;
	private Date  	 createMillis		;
	private Date	 updateMillis		;
	private String channelCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public Date getCreateMillis() {
		return createMillis;
	}
	public void setCreateMillis(Date createMillis) {
		this.createMillis = createMillis;
	}
	public Date getUpdateMillis() {
		return updateMillis;
	}
	public void setUpdateMillis(Date updateMillis) {
		this.updateMillis = updateMillis;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
}
