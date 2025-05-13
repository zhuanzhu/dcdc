package com.egeo.components.product.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2019-03-26 10:43:53
 */
public class JdPriceDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * spuId
	 */
	private Long id;	
	private Long pid;	
	private Long spuId;	
	private Integer priceType;
	private String priceValue;
	private Long enterpriseId;
	private Integer audit=1;
	
	public Long getPid() {
		return pid;
	}
	public String getPidStr() {
		return pid==null?"":String.valueOf(pid);
	}
	public String getEnterprisePidStr() {
		return pid==null?(enterpriseId+"-"):(enterpriseId+"-"+String.valueOf(pid));
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSpuId() {
		return spuId;
	}
	public String getSpuIdStr() {
		return spuId==null?(pid==null?"":String.valueOf(pid)):String.valueOf(spuId);
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
	