package com.egeo.components.product.dto;

import java.util.Date;

import com.egeo.components.product.vo.JdMerchantConfigVO;

public class JdMerchantConfigDTO {
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
	private Date createMillis;
	private Date updateMillis;
	public JdMerchantConfigDTO(){
		
	}
	public JdMerchantConfigDTO(JdMerchantConfigVO vo){
		this.id = vo.getId();
		this.merchantId = vo.getMerchantId();
		this.type = vo.getType();
		this.plateformAddtion = vo.getPlateformAddtion();
		this.priceAddtion = vo.getPriceAddtion();
		this.priceAddtionMax = vo.getPriceAddtionMax();
		this.priceAddtionMin = vo.getPriceAddtionMin();
		this.grossMarginMax = vo.getGrossMarginMax();
		this.grossMarginMin = vo.getGrossMarginMin();
		this.jdCategorys = vo.getJdCategorys();
		this.jdPriceMax = vo.getJdPriceMax();
		this.jdPriceMin = vo.getJdPriceMin();
	}
	
	public String getPlateformAddtion() {
		return plateformAddtion;
	}
	public void setPlateformAddtion(String plateformAddtion) {
		this.plateformAddtion = plateformAddtion;
	}
	public String getJdCategorys() {
		return jdCategorys;
	}
	public void setJdCategorys(String jdCategorys) {
		this.jdCategorys = jdCategorys;
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
	public String getJd_categorys() {
		return jdCategorys;
	}
	public void setJd_categorys(String jdCategorys) {
		this.jdCategorys = jdCategorys;
	}
	public String getJdPriceMax() {
		return jdPriceMax;
	}
	public Double getJdPriceMaxDouble() {
		return Double.valueOf(jdPriceMax);
	}
	public void setJdPriceMax(String jdPriceMax) {
		this.jdPriceMax = jdPriceMax;
	}
	public String getJdPriceMin() {
		return jdPriceMin;
	}
	public Double getJdPriceMinDouble() {
		return Double.valueOf(jdPriceMin);
	}
	public void setJdPriceMin(String jdPriceMin) {
		this.jdPriceMin = jdPriceMin;
	}
	
}
