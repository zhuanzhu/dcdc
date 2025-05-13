package com.egeo.components.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-06-13 15:54:11
 */
public class LimitRuleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 序列号
	 */
	private String serialNumber;	

	/**
	 * 限购规则类型： 1、个人 2、公司
	 */
	private Integer type;	

	/**
	 * 限购规则名称
	 */
	private String name;	

	/**
	 * 是否启用：0否 1是
	 */
	private Integer isStart;	

	/**
	 * su商品id
	 */
	private Long standardUnitId;	

	/**
	 * su商品序列号
	 */
	private String standardUnitSerialNumber;	

	/**
	 * 是否限量：0、否 1是
	 */
	private Integer isLimit;	

	/**
	 * su商品限售数量
	 */
	private Long suLimitNum;	

	/**
	 * 限购时间类型：1、按时间段限购 2、按周期限购
	 */
	private Integer limitTimeType;	

	/**
	 * 限购开始时间
	 */
	private Date limitOriginTime;	

	/**
	 * 限购终止时间
	 */
	private Date limitStopTime;	

	/**
	 * 周期类型：1、按日限购 2、按月限购 3、按年限购
	 */
	private Integer periodType;	

	/**
	 * 用户限购数量
	 */
	private Long userLimitNum;	

	/**
	 * 用户限购金额
	 */
	private BigDecimal userMoneySum;	

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
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * 限购对象，逗号分割存储 0-不限购 1-按用户限购 2-按企业限购 3-按门店限购
	 */
	private String limitUnit;
	/**
	 * 限购目标：1-商品 2-商品组
	 */
	private Integer limitTarget;
	/**
	 * 商品组合id
	 */
	private Long suCombId;
	/**
	 * 限售总金额
	 */
	private BigDecimal suLimitAmount;
	/**
	 * 公司类型 0:正式公司 1:测试公司 2:竞品公司
	 */
	private Integer companyType;
	/**
	 * 用户是否限量：0、否 1是
	 */
	private Integer isUserLimit;
	/**
	 * 企业是否限量：0、否 1是
	 */
	private Integer isCompanyLimit;
	/**
	 * 企业限购数量
	 */
	private Long companyLimitNum;
	/**
	 * 企业限购金额
	 */
	private BigDecimal companyMoneySum;
	/**
	 * 门店是否限量：0、否 1是
	 */
	private Integer isStoreLimit;
	/**
	 * 门店限购数量
	 */
	private Long storeLimitNum;
	/**
	 * 门店限购金额
	 */
	private BigDecimal storeMoneySum;
	
	public String getLimitUnit() {
		return limitUnit;
	}

	public void setLimitUnit(String limitUnit) {
		this.limitUnit = limitUnit;
	}

	public Integer getLimitTarget() {
		return limitTarget;
	}

	public void setLimitTarget(Integer limitTarget) {
		this.limitTarget = limitTarget;
	}

	public Long getSuCombId() {
		return suCombId;
	}

	public void setSuCombId(Long suCombId) {
		this.suCombId = suCombId;
	}

	public BigDecimal getSuLimitAmount() {
		return suLimitAmount;
	}

	public void setSuLimitAmount(BigDecimal suLimitAmount) {
		this.suLimitAmount = suLimitAmount;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Integer getIsUserLimit() {
		return isUserLimit;
	}

	public void setIsUserLimit(Integer isUserLimit) {
		this.isUserLimit = isUserLimit;
	}

	public Integer getIsCompanyLimit() {
		return isCompanyLimit;
	}

	public void setIsCompanyLimit(Integer isCompanyLimit) {
		this.isCompanyLimit = isCompanyLimit;
	}

	public Long getCompanyLimitNum() {
		return companyLimitNum;
	}

	public void setCompanyLimitNum(Long companyLimitNum) {
		this.companyLimitNum = companyLimitNum;
	}

	public BigDecimal getCompanyMoneySum() {
		return companyMoneySum;
	}

	public void setCompanyMoneySum(BigDecimal companyMoneySum) {
		this.companyMoneySum = companyMoneySum;
	}

	public Integer getIsStoreLimit() {
		return isStoreLimit;
	}

	public void setIsStoreLimit(Integer isStoreLimit) {
		this.isStoreLimit = isStoreLimit;
	}

	public Long getStoreLimitNum() {
		return storeLimitNum;
	}

	public void setStoreLimitNum(Long storeLimitNum) {
		this.storeLimitNum = storeLimitNum;
	}

	public BigDecimal getStoreMoneySum() {
		return storeMoneySum;
	}

	public void setStoreMoneySum(BigDecimal storeMoneySum) {
		this.storeMoneySum = storeMoneySum;
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
	 * 序列号
	 * @return 序列号
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * 序列号
	 * @param serialNumber 序列号
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	/**
	 * 限购规则类型： 1、个人 2、公司
	 * @return 限购规则类型： 1、个人 2、公司
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 限购规则类型： 1、个人 2、公司
	 * @param type 限购规则类型： 1、个人 2、公司
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 限购规则名称
	 * @return 限购规则名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 限购规则名称
	 * @param name 限购规则名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 是否启用：0否 1是
	 * @return 是否启用：0否 1是
	 */
	public Integer getIsStart() {
		return isStart;
	}

	/**
	 * 是否启用：0否 1是
	 * @param isStart 是否启用：0否 1是
	 */
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}
	/**
	 * su商品id
	 * @return su商品id
	 */
	public Long getStandardUnitId() {
		return standardUnitId;
	}

	/**
	 * su商品id
	 * @param standardUnitId su商品id
	 */
	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}
	/**
	 * su商品序列号
	 * @return su商品序列号
	 */
	public String getStandardUnitSerialNumber() {
		return standardUnitSerialNumber;
	}

	/**
	 * su商品序列号
	 * @param standardUnitSerialNumber su商品序列号
	 */
	public void setStandardUnitSerialNumber(String standardUnitSerialNumber) {
		this.standardUnitSerialNumber = standardUnitSerialNumber;
	}
	/**
	 * 是否限量：0、否 1是
	 * @return 是否限量：0、否 1是
	 */
	public Integer getIsLimit() {
		return isLimit;
	}

	/**
	 * 是否限量：0、否 1是
	 * @param isLimit 是否限量：0、否 1是
	 */
	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}
	/**
	 * su商品限售数量
	 * @return su商品限售数量
	 */
	public Long getSuLimitNum() {
		return suLimitNum;
	}

	/**
	 * su商品限售数量
	 * @param suLimitNum su商品限售数量
	 */
	public void setSuLimitNum(Long suLimitNum) {
		this.suLimitNum = suLimitNum;
	}
	/**
	 * 限购时间类型：1、按时间段限购 2、按周期限购
	 * @return 限购时间类型：1、按时间段限购 2、按周期限购
	 */
	public Integer getLimitTimeType() {
		return limitTimeType;
	}

	/**
	 * 限购时间类型：1、按时间段限购 2、按周期限购
	 * @param limitTimeType 限购时间类型：1、按时间段限购 2、按周期限购
	 */
	public void setLimitTimeType(Integer limitTimeType) {
		this.limitTimeType = limitTimeType;
	}
	/**
	 * 限购开始时间
	 * @return 限购开始时间
	 */
	public Date getLimitOriginTime() {
		return limitOriginTime;
	}

	/**
	 * 限购开始时间
	 * @param limitOriginTime 限购开始时间
	 */
	public void setLimitOriginTime(Date limitOriginTime) {
		this.limitOriginTime = limitOriginTime;
	}
	/**
	 * 限购终止时间
	 * @return 限购终止时间
	 */
	public Date getLimitStopTime() {
		return limitStopTime;
	}

	/**
	 * 限购终止时间
	 * @param limitStopTime 限购终止时间
	 */
	public void setLimitStopTime(Date limitStopTime) {
		this.limitStopTime = limitStopTime;
	}
	/**
	 * 周期类型：1、按日限购 2、按月限购 3、按年限购
	 * @return 周期类型：1、按日限购 2、按月限购 3、按年限购
	 */
	public Integer getPeriodType() {
		return periodType;
	}

	/**
	 * 周期类型：1、按日限购 2、按月限购 3、按年限购
	 * @param periodType 周期类型：1、按日限购 2、按月限购 3、按年限购
	 */
	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}
	
	/**
	 * 用户限购数量
	 * @return 用户限购数量
	 */
	public Long getUserLimitNum() {
		return userLimitNum;
	}

	/**
	 * 用户限购数量
	 * @param userLimitNum 用户限购数量
	 */
	public void setUserLimitNum(Long userLimitNum) {
		this.userLimitNum = userLimitNum;
	}
	/**
	 * 用户限购金额
	 * @return 用户限购金额
	 */
	public BigDecimal getUserMoneySum() {
		return userMoneySum;
	}

	/**
	 * 用户限购金额
	 * @param userMoneySum 用户限购金额
	 */
	public void setUserMoneySum(BigDecimal userMoneySum) {
		this.userMoneySum = userMoneySum;
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
}
	