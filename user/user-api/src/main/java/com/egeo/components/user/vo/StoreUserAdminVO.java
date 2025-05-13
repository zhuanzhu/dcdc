package com.egeo.components.user.vo;

import java.io.Serializable;

public class StoreUserAdminVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long companyId;
	
	private Long storeId;
	private Long platformId;
	
	private String mobile;
	
	private Integer account_status;

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

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAccount_status() {
		return account_status;
	}

	public void setAccount_status(Integer account_status) {
		this.account_status = account_status;
	}
	
	
	
}
