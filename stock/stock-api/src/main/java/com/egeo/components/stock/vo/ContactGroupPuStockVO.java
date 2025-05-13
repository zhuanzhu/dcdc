package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mingqiang.luo
 * @date 2018-12-19 10:05:34
 */
public class ContactGroupPuStockVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 商品Id
	 */
	private Long suId;
	/**
	 * puId
	 */
	private Long puId;
	/**
	 * sku组ID
	 */
	private Long contactGroupSkuId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

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
	 * 商品Id
	 * @return 商品Id
	 */
	public Long getSuId() {
		return suId;
	}

	/**
	 * 商品Id
	 * @param suId 商品Id
	 */
	public void setSuId(Long suId) {
		this.suId = suId;
	}	
	/**
	 * puId
	 * @return puId
	 */
	public Long getPuId() {
		return puId;
	}

	/**
	 * puId
	 * @param puId puId
	 */
	public void setPuId(Long puId) {
		this.puId = puId;
	}	
	/**
	 * sku组ID
	 * @return sku组ID
	 */
	public Long getContactGroupSkuId() {
		return contactGroupSkuId;
	}

	/**
	 * sku组ID
	 * @param contactGroupSkuId sku组ID
	 */
	public void setContactGroupSkuId(Long contactGroupSkuId) {
		this.contactGroupSkuId = contactGroupSkuId;
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
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
}
	