package com.egeo.components.user.vo;

import java.io.Serializable;

public class CompanyIdAndNameVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id ;
	private String companyName ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	

}
