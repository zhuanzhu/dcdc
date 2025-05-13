package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

import com.egeo.components.product.dto.JdPriceDTO;

/**
 * 
 * @author tan
 * @date 2019-03-29 10:17:52
 */
public class JdPriceAuditingVO extends JdPriceDTO {
	private static final long serialVersionUID = 1L;

	/**
	 * 产品名称
	 */
	private String name;
	/**
	 * 类目名称
	 */
	private String categoryName;
	/**
	 * 
	 */
	private String jdPrice;
	/**
	 * 
	 */
	private String marketPrice;
	/**
	 * 
	 */
	private String enterprisePrice;
	/**
	 * 
	 */
	private String profit;
	private Integer isSelf;
	private Integer isJdLogistics;
	
	public JdPriceAuditingVO(JdPriceDTO data) {
		this.setAudit(data.getAudit());
		this.setEnterpriseId(data.getEnterpriseId());
		this.setId(data.getId());
		this.setPid(data.getPid());
		this.setPriceType(data.getPriceType());
		this.setPriceValue(data.getPriceValue());
		this.setSpuId(data.getSpuId());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getJdPrice() {
		return jdPrice;
	}
	public void setJdPrice(String jdPrice) {
		this.jdPrice = jdPrice;
	}
	public String getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}
	public String getEnterprisePrice() {
		return enterprisePrice;
	}
	public void setEnterprisePrice(String enterprisePrice) {
		this.enterprisePrice = enterprisePrice;
	}
	public String getProfit() {
		return profit;
	}
	public void setProfit(String profit) {
		this.profit = profit;
	}
	public Integer getIsSelf() {
		return isSelf;
	}
	public void setIsSelf(Integer isSelf) {
		this.isSelf = isSelf;
	}
	public Integer getIsJdLogistics() {
		return isJdLogistics;
	}
	public void setIsJdLogistics(Integer isJdLogistics) {
		this.isJdLogistics = isJdLogistics;
	}
	
}
	