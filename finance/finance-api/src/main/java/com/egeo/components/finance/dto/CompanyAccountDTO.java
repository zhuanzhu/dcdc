package com.egeo.components.finance.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-06 15:24:26
 */
public class CompanyAccountDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 账户名称
	 */
	private String name;	

	/**
	 * 账户余额
	 */
	private BigDecimal balance;
	
	private String ciphertext;
	
	private String uuid;

	/**
	 * 类型 0:迩格充值发放账户 1:迩格积分收入账户 2:迩格现金收入账户 20:客户企业账户
	 */
	private Integer type;	

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 有效性 0:有效 1:失效
	 */
	private Integer disabled;	

	/**
	 * 最后充值时间
	 */
	private Date lastRechargeTime;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	private Long platformId;

	/**
	 * 企业失效前金额
	 */
	private BigDecimal beforeDisableBalance;


	public BigDecimal getBeforeDisableBalance() {
		return beforeDisableBalance;
	}

	public void setBeforeDisableBalance(BigDecimal beforeDisableBalance) {
		this.beforeDisableBalance = beforeDisableBalance;
	}
	
	public String getCiphertext() {
		return ciphertext;
	}

	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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
	 * 账户名称
	 * @return 账户名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 账户名称
	 * @param name 账户名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 账户余额
	 * @return 账户余额
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 账户余额
	 * @param balance 账户余额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * 类型 0:迩格充值发放账户 1:迩格积分收入账户 2:迩格现金收入账户 20:客户企业账户
	 * @return 类型 0:迩格充值发放账户 1:迩格积分收入账户 2:迩格现金收入账户 20:客户企业账户
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 0:迩格充值发放账户 1:迩格积分收入账户 2:迩格现金收入账户 20:客户企业账户
	 * @param type 类型 0:迩格充值发放账户 1:迩格积分收入账户 2:迩格现金收入账户 20:客户企业账户
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 公司id
	 * @return 公司id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司id
	 * @param companyId 公司id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	/**
	 * 有效性 0:有效 1:失效
	 * @return 有效性 0:有效 1:失效
	 */
	public Integer getDisabled() {
		return disabled;
	}

	/**
	 * 有效性 0:有效 1:失效
	 * @param disabled 有效性 0:有效 1:失效
	 */
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}
	/**
	 * 最后充值时间
	 * @return 最后充值时间
	 */
	public Date getLastRechargeTime() {
		return lastRechargeTime;
	}

	/**
	 * 最后充值时间
	 * @param lastRechargeTime 最后充值时间
	 */
	public void setLastRechargeTime(Date lastRechargeTime) {
		this.lastRechargeTime = lastRechargeTime;
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

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	
	
}
	