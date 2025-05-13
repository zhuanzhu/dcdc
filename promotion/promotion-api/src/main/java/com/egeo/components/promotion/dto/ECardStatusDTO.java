package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author paul
 * @date 2018-08-29 05:14:25
 */
public class ECardStatusDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	private String id;
	/**
	 *
	 */
	private Long skuId;
	/**
	 * 开始时间
	 */
	private Date startTime;	
	/**
	 * 结束时间
	 */
	private Date endTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
	