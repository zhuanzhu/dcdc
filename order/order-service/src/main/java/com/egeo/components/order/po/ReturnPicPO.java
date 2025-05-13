package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class ReturnPicPO {


	private Long id;

	/**
	 * 退货单编号
	 */
	private Long returnId;	

	/**
	 * 图片url
	 */
	private String picUrl;	

	/**
	 * 图片类型 1:退货图片 2：收件图片
	 */
	private Integer type;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	public Long getId() {
		return id;
	}

	/**
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 退货单编号
	 * @return 退货单编号
	 */
	public Long getReturnId() {
		return returnId;
	}

	/**
	 * 退货单编号
	 * @param returnId 退货单编号
	 */
	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}

	/**
	 * 图片url
	 * @return 图片url
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * 图片url
	 * @param picUrl 图片url
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * 图片类型 1:退货图片 2：收件图片
	 * @return 图片类型 1:退货图片 2：收件图片
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 图片类型 1:退货图片 2：收件图片
	 * @param type 图片类型 1:退货图片 2：收件图片
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
	