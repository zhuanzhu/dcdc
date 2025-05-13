package com.egeo.components.stock.po;


import java.util.Date;

/**
 * 
 * @author mingqiang.luo
 * @date 2018-12-19 10:05:33
 */
public class ContactGroupSkuStockPO {


	private Long id;

	/**
	 * skuId
	 */
	private Long skuId;	

	/**
	 * 库存关联组ID
	 */
	private Long contactGroupStockId;	

	/**
	 * 
	 */
	private Date createTime;	

	/**
	 * 
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
	 * skuId
	 * @return skuId
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * skuId
	 * @param skuId skuId
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	/**
	 * 库存关联组ID
	 * @return 库存关联组ID
	 */
	public Long getContactGroupStockId() {
		return contactGroupStockId;
	}

	/**
	 * 库存关联组ID
	 * @param contactGroupStockId 库存关联组ID
	 */
	public void setContactGroupStockId(Long contactGroupStockId) {
		this.contactGroupStockId = contactGroupStockId;
	}

	/**
	 * 
	 * @return 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
}
	