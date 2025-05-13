package com.egeo.components.stock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author mingqiang.luo
 * @date 2018-12-19 10:05:34
 */
public class ContactGroupStockVO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 编辑人姓名
	 */
	private  String createUserName;

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 编辑人id
	 * @return
	 */
	private Long createUserId;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 运营方id
	 */
	private Long merchantId;

	private Long id;
	/**
	 * 关联组名称
	 */
	private String name;
	/**
	 * 产品ID
	 */
	private Long spuId;
	/**
	 * 关联商品数
	 */
	private Integer count;
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
	 * 关联组名称
	 * @return 关联组名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 关联组名称
	 * @param name 关联组名称
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 产品ID
	 * @return 产品ID
	 */
	public Long getSpuId() {
		return spuId;
	}

	/**
	 * 产品ID
	 * @param spuId 产品ID
	 */
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}	
	/**
	 * 关联商品数
	 * @return 关联商品数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 关联商品数
	 * @param count 关联商品数
	 */
	public void setCount(Integer count) {
		this.count = count;
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
	