package com.egeo.components.product.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2019-03-26 10:43:53
 */
public class JdPricePO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * spuId
	 */
	private Long id;	
	private Long spuId;	
	private Integer priceType;
	private String priceValue;
	private Long enterpriseId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSpuId() {
		return spuId;
	}
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
	public Integer getPriceType() {
		return priceType;
	}
	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}
	public String getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(String priceValue) {
		this.priceValue = priceValue;
	}
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
}
	