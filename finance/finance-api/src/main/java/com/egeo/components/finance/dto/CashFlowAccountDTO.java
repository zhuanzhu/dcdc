package com.egeo.components.finance.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 资金流数据传递类
 * 
 * @author GRACIA
 *
 */
public class CashFlowAccountDTO implements Serializable {

	private static final long serialVersionUID = -1331043014015716420L;

	private Long accountId;

	private String salt;

	private BigDecimal sum;

	/**
	 * 子备注，用于记录员工充值导入时的每行备注
	 */
	private String subRemark;

	private Integer userAccountType;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 公司id
	 */
	private Long companyId;	

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getUserAccountType() {
		return userAccountType;
	}

	public void setUserAccountType(Integer userAccountType) {
		this.userAccountType = userAccountType;
	}

	public String getSubRemark() {
		return subRemark;
	}

	public void setSubRemark(String subRemark) {
		this.subRemark = subRemark;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

}
