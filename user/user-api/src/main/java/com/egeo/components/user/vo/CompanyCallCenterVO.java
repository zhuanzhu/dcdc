package com.egeo.components.user.vo;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-01-14 12:40:00
 */
public class CompanyCallCenterVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 客服电话
	 */
	private String callCenterPhone;
	/**
	 * 企业qq
	 */
	private String companyQq;
	/**
	 * 企业微信
	 */
	private String companyWeixin;

	/**
	 * 客服电话
	 * @return 客服电话
	 */
	public String getCallCenterPhone() {
		return callCenterPhone;
	}

	/**
	 * 客服电话
	 * @param callCenterPhone 客服电话
	 */
	public void setCallCenterPhone(String callCenterPhone) {
		this.callCenterPhone = callCenterPhone;
	}	
	/**
	 * 企业qq
	 * @return 企业qq
	 */
	public String getCompanyQq() {
		return companyQq;
	}

	/**
	 * 企业qq
	 * @param companyQq 企业qq
	 */
	public void setCompanyQq(String companyQq) {
		this.companyQq = companyQq;
	}	
	/**
	 * 企业微信
	 * @return 企业微信
	 */
	public String getCompanyWeixin() {
		return companyWeixin;
	}

	/**
	 * 企业微信
	 * @param companyWeixin 企业微信
	 */
	public void setCompanyWeixin(String companyWeixin) {
		this.companyWeixin = companyWeixin;
	}	
}
	