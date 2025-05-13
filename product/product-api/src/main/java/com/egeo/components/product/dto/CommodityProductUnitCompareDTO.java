package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author paul
 * @date 2018-09-13 18:06:29
 */
public class CommodityProductUnitCompareDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 
	 */
	private String platformName;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 
	 */
	private Integer status;	

	/**
	 * 
	 */
	private Date updateTime;	
	
	private Long platformId;

	/**
	 * 
	 */
	private Integer operator;	

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @return 
	 */
	public String getPlatformName() {
		return platformName;
	}

	/**
	 * 
	 * @param platformName 
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 
	 * @return 
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 
	 * @return 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 
	 * @return 
	 */
	public Integer getOperator() {
		return operator;
	}

	/**
	 * 
	 * @param operator 
	 */
	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	
}
	