package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:24
 */
public class ExpressMapConfigPO {


	private Long id;

	/**
	 * 公司类别
	 */
	private Integer deliveryType;	

	/**
	 * 物流公司名称
	 */
	private String deliveryCompanyName;	

	/**
	 * 快递公司id
	 */
	private String expressCompanyId;	

	/**
	 * 快递公司名称
	 */
	private String expressCompanyName;	

	/**
	 * 本地快递公司id
	 */
	private String deliveryCompanyId;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 公司类别
	 * @return 公司类别
	 */
	public Integer getDeliveryType() {
		return deliveryType;
	}

	/**
	 * 公司类别
	 * @param deliveryType 公司类别
	 */
	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	/**
	 * 物流公司名称
	 * @return 物流公司名称
	 */
	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	/**
	 * 物流公司名称
	 * @param deliveryCompanyName 物流公司名称
	 */
	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}

	/**
	 * 快递公司id
	 * @return 快递公司id
	 */
	public String getExpressCompanyId() {
		return expressCompanyId;
	}

	/**
	 * 快递公司id
	 * @param expressCompanyId 快递公司id
	 */
	public void setExpressCompanyId(String expressCompanyId) {
		this.expressCompanyId = expressCompanyId;
	}

	/**
	 * 快递公司名称
	 * @return 快递公司名称
	 */
	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	/**
	 * 快递公司名称
	 * @param expressCompanyName 快递公司名称
	 */
	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	/**
	 * 本地快递公司id
	 * @return 本地快递公司id
	 */
	public String getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	/**
	 * 本地快递公司id
	 * @param deliveryCompanyId 本地快递公司id
	 */
	public void setDeliveryCompanyId(String deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
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
}
	