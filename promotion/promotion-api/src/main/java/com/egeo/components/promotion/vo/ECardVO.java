package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-10 05:14:25
 */
public class ECardVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 批次
	 */
	private Long batch;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * sku名称
	 */
	private String skuName;
	/**
	 * sku编号
	 */
	private String skuSerialNumber;
	/**
	 * 1、截至日期之前 2、截至日期之后
	 */
	private Integer type;
	/**
	 * 卡号
	 */
	private String cardNumber;
	/**
	 * 卡密
	 */
	private String cardThick;
	/**
	 * uuid
	 */
	private String uuid;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 面值
	 */
	private BigDecimal faceValue;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建人ID
	 */
	private Long createUserid;
	/**
	 * 创建人姓名
	 */
	private String createUsername;
	/**
	 * 创建人IP
	 */
	private String createUserip;
	/**
	 * 创建人MAC地址
	 */
	private String createUsermac;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;
	/**
	 * 最后修改人ID
	 */
	private Long updateUserid;
	/**
	 * 最后修改人姓名
	 */
	private String updateUsername;
	/**
	 * 最后修改人IP
	 */
	private String updateUserip;
	/**
	 * 最后修改人MAC
	 */
	private String updateUsermac;
	/**
	 * 客户端程序的版本号
	 */
	private String clientVersionno;
	/**
	 * 是否有效：0否、1是
	 */
	private Integer isValid;
	/**
	 * 订单编号
	 */
	private String orderCode;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户登录名
	 */
	private String userLoginName;
	/**
	 * 是否分配：0否、1是
	 */
	private Integer isAllocation;
	/**
	 * 分配时间
	 */
	private Date allocationTime;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	/**
	 * 平台id
	 */
	private Long platformId;

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
	 * 批次
	 * @return 批次
	 */
	public Long getBatch() {
		return batch;
	}

	/**
	 * 批次
	 * @param batch 批次
	 */
	public void setBatch(Long batch) {
		this.batch = batch;
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
	 * sku编号
	 * @return sku编号
	 */
	public String getSkuSerialNumber() {
		return skuSerialNumber;
	}

	/**
	 * sku编号
	 * @param skuSerialNumber sku编号
	 */
	public void setSkuSerialNumber(String skuSerialNumber) {
		this.skuSerialNumber = skuSerialNumber;
	}	
	/**
	 * 卡类型：1、京东e卡
	 * @return 卡类型：1、京东e卡
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 卡类型：1、京东e卡
	 * @param type 卡类型：1、京东e卡
	 */
	public void setType(Integer type) {
		this.type = type;
	}	
	/**
	 * 卡号
	 * @return 卡号
	 */
	public String getCardNumber() {
		return cardNumber;
	}

	/**
	 * 卡号
	 * @param cardNumber 卡号
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}	
	/**
	 * 卡密
	 * @return 卡密
	 */
	public String getCardThick() {
		return cardThick;
	}

	/**
	 * 卡密
	 * @param cardThick 卡密
	 */
	public void setCardThick(String cardThick) {
		this.cardThick = cardThick;
	}	
	/**
	 * uuid
	 * @return uuid
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * uuid
	 * @param uuid uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}	
	/**
	 * 开始时间
	 * @return 开始时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 开始时间
	 * @param startTime 开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}	
	/**
	 * 结束时间
	 * @return 结束时间
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 结束时间
	 * @param endTime 结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}	
	/**
	 * 来源
	 * @return 来源
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 来源
	 * @param source 来源
	 */
	public void setSource(String source) {
		this.source = source;
	}	
	/**
	 * 面值
	 * @return 面值
	 */
	public BigDecimal getFaceValue() {
		return faceValue;
	}

	/**
	 * 面值
	 * @param faceValue 面值
	 */
	public void setFaceValue(BigDecimal faceValue) {
		this.faceValue = faceValue;
	}	
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}	
	/**
	 * 创建人ID
	 * @return 创建人ID
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 创建人ID
	 * @param createUserid 创建人ID
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}	
	/**
	 * 创建人姓名
	 * @return 创建人姓名
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * 创建人姓名
	 * @param createUsername 创建人姓名
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}	
	/**
	 * 创建人IP
	 * @return 创建人IP
	 */
	public String getCreateUserip() {
		return createUserip;
	}

	/**
	 * 创建人IP
	 * @param createUserip 创建人IP
	 */
	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}	
	/**
	 * 创建人MAC地址
	 * @return 创建人MAC地址
	 */
	public String getCreateUsermac() {
		return createUsermac;
	}

	/**
	 * 创建人MAC地址
	 * @param createUsermac 创建人MAC地址
	 */
	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
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
	 * 最后修改人ID
	 * @return 最后修改人ID
	 */
	public Long getUpdateUserid() {
		return updateUserid;
	}

	/**
	 * 最后修改人ID
	 * @param updateUserid 最后修改人ID
	 */
	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}	
	/**
	 * 最后修改人姓名
	 * @return 最后修改人姓名
	 */
	public String getUpdateUsername() {
		return updateUsername;
	}

	/**
	 * 最后修改人姓名
	 * @param updateUsername 最后修改人姓名
	 */
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}	
	/**
	 * 最后修改人IP
	 * @return 最后修改人IP
	 */
	public String getUpdateUserip() {
		return updateUserip;
	}

	/**
	 * 最后修改人IP
	 * @param updateUserip 最后修改人IP
	 */
	public void setUpdateUserip(String updateUserip) {
		this.updateUserip = updateUserip;
	}	
	/**
	 * 最后修改人MAC
	 * @return 最后修改人MAC
	 */
	public String getUpdateUsermac() {
		return updateUsermac;
	}

	/**
	 * 最后修改人MAC
	 * @param updateUsermac 最后修改人MAC
	 */
	public void setUpdateUsermac(String updateUsermac) {
		this.updateUsermac = updateUsermac;
	}	
	/**
	 * 客户端程序的版本号
	 * @return 客户端程序的版本号
	 */
	public String getClientVersionno() {
		return clientVersionno;
	}

	/**
	 * 客户端程序的版本号
	 * @param clientVersionno 客户端程序的版本号
	 */
	public void setClientVersionno(String clientVersionno) {
		this.clientVersionno = clientVersionno;
	}	
	/**
	 * 是否有效：0否、1是
	 * @return 是否有效：0否、1是
	 */
	public Integer getIsValid() {
		return isValid;
	}

	/**
	 * 是否有效：0否、1是
	 * @param isValid 是否有效：0否、1是
	 */
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
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
	 * 用户登录名
	 * @return 用户登录名
	 */
	public String getUserLoginName() {
		return userLoginName;
	}

	/**
	 * 用户登录名
	 * @param userLoginName 用户登录名
	 */
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}	
	/**
	 * 是否分配：0否、1是
	 * @return 是否分配：0否、1是
	 */
	public Integer getIsAllocation() {
		return isAllocation;
	}

	/**
	 * 是否分配：0否、1是
	 * @param isAllocation 是否分配：0否、1是
	 */
	public void setIsAllocation(Integer isAllocation) {
		this.isAllocation = isAllocation;
	}	
	/**
	 * 分配时间
	 * @return 分配时间
	 */
	public Date getAllocationTime() {
		return allocationTime;
	}

	/**
	 * 分配时间
	 * @param allocationTime 分配时间
	 */
	public void setAllocationTime(Date allocationTime) {
		this.allocationTime = allocationTime;
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

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}	
	
	
}
	