package com.egeo.components.finance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 后台公司账户列表vo
 * @author jiang
 * @date 2018-01-06 15:25:31
 */
public class CompanyAccountVO implements Serializable {
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
	/**
	 * 类型 0:迩格充值发放账户 1:迩格积分收入账户 2:迩格现金收入账户 20:客户企业账户
	 */
	private Integer type;
	/**
	 * 公司id
	 */
	private Long companyId;
	
	private String companyName;
	
	private String platformId;
	
	/**
	 * 有效性 0:有效 1:失效
	 */
	private Integer disabled;
	/**
	 * 创建时间
	 */
	private String createTime;

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}	
	
	
}
	