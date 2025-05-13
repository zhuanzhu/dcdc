package com.egeo.components.product.dto;

import com.egeo.components.product.vo.ChannelMerchantConfigVO;

import java.util.Date;

public class ChannelMerchantConfigDTO {
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
	private Date createMillis;
	private Date updateMillis;
	private String channelCode;
	public ChannelMerchantConfigDTO(){

	}
	public ChannelMerchantConfigDTO(ChannelMerchantConfigVO vo){
		this.id = vo.getId();
		this.merchantId = vo.getMerchantId();
		this.type = vo.getType();
		this.plateformAddtion = vo.getPlateformAddtion();
		this.priceAddtion = vo.getPriceAddtion();
		this.priceAddtionMax = vo.getPriceAddtionMax();
		this.priceAddtionMin = vo.getPriceAddtionMin();
		this.grossMarginMax = vo.getGrossMarginMax();
		this.grossMarginMin = vo.getGrossMarginMin();
		this.channelCategorys = vo.getChannelCategorys();
		this.channelPriceMax = vo.getChannelPriceMax();
		this.channelPriceMin = vo.getChannelPriceMin();
		this.channelCode = vo.getChannelCode();
	}

	public ChannelMerchantConfigDTO(JdMerchantConfigDTO dto){
		this.id = dto.getId();
		this.merchantId = dto.getMerchantId();
		this.type = dto.getType();
		this.plateformAddtion = dto.getPlateformAddtion();
		this.priceAddtion = dto.getPriceAddtion();
		this.priceAddtionMax = dto.getPriceAddtionMax();
		this.priceAddtionMin = dto.getPriceAddtionMin();
		this.grossMarginMax = dto.getGrossMarginMax();
		this.grossMarginMin = dto.getGrossMarginMin();
		this.channelCategorys = dto.getJdCategorys();
		this.channelPriceMax = dto.getJdPriceMax();
		this.channelPriceMin = dto.getJdPriceMin();
	}

	public String getPlateformAddtion() {
		return plateformAddtion;
	}
	public void setPlateformAddtion(String plateformAddtion) {
		this.plateformAddtion = plateformAddtion;
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
	public Double getChannelPriceMaxDouble() {
		return channelPriceMax !=null?Double.valueOf(channelPriceMax):null;
	}
	public Double getChannelPriceMinDouble() {
		return channelPriceMin !=null ? Double.valueOf(channelPriceMin):null;
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
