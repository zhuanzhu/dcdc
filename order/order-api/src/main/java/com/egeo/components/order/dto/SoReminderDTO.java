package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoReminderDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 催单描述
	 */
	private String reminderDesc;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 用户电话
	 */
	private String userPhone;	

	/**
	 * 来电记录
	 */
	private String phoneRecord;	

	/**
	 * 催单类型 1:接线  2:外呼
	 */
	private Integer reminderType;	

	/**
	 * 问题分类ID
	 */
	private Long askCategoryId;	

	/**
	 * 问题分类描述
	 */
	private String askCategoryDesc;	

	/**
	 * 催单对象 1:商家  2:客户
	 */
	private Integer reminderObject;	

	/**
	 * 问题二级分类
	 */
	private Long askCategoryTwoId;	

	/**
	 * 流水状态 1：已完成 2： 未完成
	 */
	private Integer status;	

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
	 * 订单编号
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号
	 * @param orderCode 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 催单描述
	 * @return 催单描述
	 */
	public String getReminderDesc() {
		return reminderDesc;
	}

	/**
	 * 催单描述
	 * @param reminderDesc 催单描述
	 */
	public void setReminderDesc(String reminderDesc) {
		this.reminderDesc = reminderDesc;
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
	 * 商家id
	 * @return 商家id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家id
	 * @param merchantId 商家id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * 用户电话
	 * @return 用户电话
	 */
	public String getUserPhone() {
		return userPhone;
	}

	/**
	 * 用户电话
	 * @param userPhone 用户电话
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	/**
	 * 来电记录
	 * @return 来电记录
	 */
	public String getPhoneRecord() {
		return phoneRecord;
	}

	/**
	 * 来电记录
	 * @param phoneRecord 来电记录
	 */
	public void setPhoneRecord(String phoneRecord) {
		this.phoneRecord = phoneRecord;
	}
	/**
	 * 催单类型 1:接线  2:外呼
	 * @return 催单类型 1:接线  2:外呼
	 */
	public Integer getReminderType() {
		return reminderType;
	}

	/**
	 * 催单类型 1:接线  2:外呼
	 * @param reminderType 催单类型 1:接线  2:外呼
	 */
	public void setReminderType(Integer reminderType) {
		this.reminderType = reminderType;
	}
	/**
	 * 问题分类ID
	 * @return 问题分类ID
	 */
	public Long getAskCategoryId() {
		return askCategoryId;
	}

	/**
	 * 问题分类ID
	 * @param askCategoryId 问题分类ID
	 */
	public void setAskCategoryId(Long askCategoryId) {
		this.askCategoryId = askCategoryId;
	}
	/**
	 * 问题分类描述
	 * @return 问题分类描述
	 */
	public String getAskCategoryDesc() {
		return askCategoryDesc;
	}

	/**
	 * 问题分类描述
	 * @param askCategoryDesc 问题分类描述
	 */
	public void setAskCategoryDesc(String askCategoryDesc) {
		this.askCategoryDesc = askCategoryDesc;
	}
	/**
	 * 催单对象 1:商家  2:客户
	 * @return 催单对象 1:商家  2:客户
	 */
	public Integer getReminderObject() {
		return reminderObject;
	}

	/**
	 * 催单对象 1:商家  2:客户
	 * @param reminderObject 催单对象 1:商家  2:客户
	 */
	public void setReminderObject(Integer reminderObject) {
		this.reminderObject = reminderObject;
	}
	/**
	 * 问题二级分类
	 * @return 问题二级分类
	 */
	public Long getAskCategoryTwoId() {
		return askCategoryTwoId;
	}

	/**
	 * 问题二级分类
	 * @param askCategoryTwoId 问题二级分类
	 */
	public void setAskCategoryTwoId(Long askCategoryTwoId) {
		this.askCategoryTwoId = askCategoryTwoId;
	}
	/**
	 * 流水状态 1：已完成 2： 未完成
	 * @return 流水状态 1：已完成 2： 未完成
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 流水状态 1：已完成 2： 未完成
	 * @param status 流水状态 1：已完成 2： 未完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	