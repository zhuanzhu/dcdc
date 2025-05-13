package com.egeo.components.order.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-06-13 17:46:25
 */
public class LimitRuleRecordPO {


	private Long id;

	/**
	 * 限购规则id
	 */
	private Long limitRuleId;	

	/**
	 * 限购规则名称
	 */
	private String limitRuleName;	

	/**
	 * 限购规则序列号
	 */
	private String limitRuleSerialNumber;	

	/**
	 * su商品序列号
	 */
	private String standardUnitSerialNumber;	

	/**
	 * puId
	 */
	private Long productUnitId;	

	/**
	 * pu序列号
	 */
	private String productUnitSerialNumber;	

	/**
	 * 用户id
	 */
	private Long createUserid;	

	/**
	 * 创建人姓名
	 */
	private String createUsername;	

	/**
	 * 创建用户手机号
	 */
	private String createUserMobile;	

	/**
	 * 订单编号
	 */
	private String orderCode;	
	/**
	 * 限购方式：1、按购买商品总量限购 2、按购买商品总金额限购
	 */
	private Integer limitType;	

	/**
	 * 购买总量
	 */
	private Long buySum;	

	/**
	 * 购买总金额
	 */
	private BigDecimal buyMoneySum;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * su商品id
	 */
	private Long standardUnitId;


	private Integer record;
	private Long companyId;
	private Long storeId;

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	private Integer orderStatus;
	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getStandardUnitId() {
		return standardUnitId;
	}

	public void setStandardUnitId(Long standardUnitId) {
		this.standardUnitId = standardUnitId;
	}

	public Integer getLimitType() {
		return limitType;
	}

	public void setLimitType(Integer limitType) {
		this.limitType = limitType;
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
	 * 限购规则id
	 * @return 限购规则id
	 */
	public Long getLimitRuleId() {
		return limitRuleId;
	}

	/**
	 * 限购规则id
	 * @param limitRuleId 限购规则id
	 */
	public void setLimitRuleId(Long limitRuleId) {
		this.limitRuleId = limitRuleId;
	}

	/**
	 * 限购规则名称
	 * @return 限购规则名称
	 */
	public String getLimitRuleName() {
		return limitRuleName;
	}

	/**
	 * 限购规则名称
	 * @param limitRuleName 限购规则名称
	 */
	public void setLimitRuleName(String limitRuleName) {
		this.limitRuleName = limitRuleName;
	}

	/**
	 * 限购规则序列号
	 * @return 限购规则序列号
	 */
	public String getLimitRuleSerialNumber() {
		return limitRuleSerialNumber;
	}

	/**
	 * 限购规则序列号
	 * @param limitRuleSerialNumber 限购规则序列号
	 */
	public void setLimitRuleSerialNumber(String limitRuleSerialNumber) {
		this.limitRuleSerialNumber = limitRuleSerialNumber;
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
	 * puId
	 * @return puId
	 */
	public Long getProductUnitId() {
		return productUnitId;
	}

	/**
	 * puId
	 * @param productUnitId puId
	 */
	public void setProductUnitId(Long productUnitId) {
		this.productUnitId = productUnitId;
	}

	/**
	 * pu序列号
	 * @return pu序列号
	 */
	public String getProductUnitSerialNumber() {
		return productUnitSerialNumber;
	}

	/**
	 * pu序列号
	 * @param productUnitSerialNumber pu序列号
	 */
	public void setProductUnitSerialNumber(String productUnitSerialNumber) {
		this.productUnitSerialNumber = productUnitSerialNumber;
	}

	/**
	 * 用户id
	 * @return 用户id
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 用户id
	 * @param createUserid 用户id
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
	 * 创建用户手机号
	 * @return 创建用户手机号
	 */
	public String getCreateUserMobile() {
		return createUserMobile;
	}

	/**
	 * 创建用户手机号
	 * @param createUserMobile 创建用户手机号
	 */
	public void setCreateUserMobile(String createUserMobile) {
		this.createUserMobile = createUserMobile;
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
	 * 购买总量
	 * @return 购买总量
	 */
	public Long getBuySum() {
		return buySum;
	}

	/**
	 * 购买总量
	 * @param buySum 购买总量
	 */
	public void setBuySum(Long buySum) {
		this.buySum = buySum;
	}

	/**
	 * 购买总金额
	 * @return 购买总金额
	 */
	public BigDecimal getBuyMoneySum() {
		return buyMoneySum;
	}

	/**
	 * 购买总金额
	 * @param buyMoneySum 购买总金额
	 */
	public void setBuyMoneySum(BigDecimal buyMoneySum) {
		this.buyMoneySum = buyMoneySum;
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
	