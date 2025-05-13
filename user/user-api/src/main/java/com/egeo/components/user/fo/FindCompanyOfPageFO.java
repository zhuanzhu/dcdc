package com.egeo.components.user.fo;

import java.util.List;

import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.orm.Pagination;

public class FindCompanyOfPageFO {
	private CompanyDTO dto;
	private Pagination page;
	private List<Long> companyIdList;
	public CompanyDTO getDto() {
		return dto;
	}
	public void setDto(CompanyDTO dto) {
		this.dto = dto;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public List<Long> getCompanyIdList() {
		return companyIdList;
	}
	public void setCompanyIdList(List<Long> companyIdList) {
		this.companyIdList = companyIdList;
	}
}
