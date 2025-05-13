package com.egeo.components.user.dto;

import java.util.List;

import com.egeo.orm.Pagination;

public class UserExtendAllByCompanyOfPageDTO {
	private List<Long> companyList;
	private Pagination page;
	public UserExtendAllByCompanyOfPageDTO(List<Long> companyList,Pagination page) {
		// TODO Auto-generated constructor stub
		this.companyList = companyList;
		this.page = page;
	}
	public List<Long> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<Long> companyList) {
		this.companyList = companyList;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
}
