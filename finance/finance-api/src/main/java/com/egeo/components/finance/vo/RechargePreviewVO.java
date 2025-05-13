package com.egeo.components.finance.vo;

import java.math.BigDecimal;

/**
 * 充值预览列表VO
 * @author GRACIA
 *
 */
public class RechargePreviewVO {

	private Long id;

	/**
	 * 位于表格的行号
	 */
	private Integer rownum;	

	/**
	 * 用户名
	 */
	private String userName;	

	/**
	 * 员工号
	 */
	private String memberCode;	

	/**
	 * 邮箱
	 */
	private String email;	

	/**
	 * 金额
	 */
	private BigDecimal sum;	

	/**
	 * 公司名称
	 */
	private String companyName;	

	private String impRes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRownum() {
		return rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getImpRes() {
		return impRes;
	}

	public void setImpRes(String impRes) {
		this.impRes = impRes;
	}	
	
	
}