package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:24
 */
public class MerchantExpressConfigPO {


	private Long id;

	/**
	 * 
	 */
	private Long merchantId;	

	/**
	 * 商家类型：1 自营 2 非自营
	 */
	private Integer type;	

	/**
	 * 快递公司ID
	 */
	private String deliveryCompanyId;	

	/**
	 * 快递公司名称
	 */
	private String deliveryCompanyName;	

	/**
	 * 电话
	 */
	private String phone;	

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
	 * 
	 * @return 
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 
	 * @param merchantId 
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 商家类型：1 自营 2 非自营
	 * @return 商家类型：1 自营 2 非自营
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 商家类型：1 自营 2 非自营
	 * @param type 商家类型：1 自营 2 非自营
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 快递公司ID
	 * @return 快递公司ID
	 */
	public String getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	/**
	 * 快递公司ID
	 * @param deliveryCompanyId 快递公司ID
	 */
	public void setDeliveryCompanyId(String deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}

	/**
	 * 快递公司名称
	 * @return 快递公司名称
	 */
	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	/**
	 * 快递公司名称
	 * @param deliveryCompanyName 快递公司名称
	 */
	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}

	/**
	 * 电话
	 * @return 电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 电话
	 * @param phone 电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	