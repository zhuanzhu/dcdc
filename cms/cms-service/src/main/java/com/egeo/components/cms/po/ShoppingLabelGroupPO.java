package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-08 18:06:59
 */
public class ShoppingLabelGroupPO {


	private Long id;

	/**
	 * 类型 0:3个 1:4个
	 */
	private Integer type;	

	/**
	 * 小对勾图片url
	 */
	private String imgUrl;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	private Long instId;

	public Long getInstId() {
		return instId;
	}

	public void setInstId(Long instId) {
		this.instId = instId;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 类型 0:3个 1:4个
	 * @return 类型 0:3个 1:4个
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 0:3个 1:4个
	 * @param type 类型 0:3个 1:4个
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 小对勾图片url
	 * @return 小对勾图片url
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 小对勾图片url
	 * @param imgUrl 小对勾图片url
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	