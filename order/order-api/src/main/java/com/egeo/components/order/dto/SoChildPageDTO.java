package com.egeo.components.order.dto;

import com.egeo.orm.Pagination;

public class SoChildPageDTO {
	private SoChildDTO dto; 
	private Pagination page;
	public SoChildPageDTO(SoChildDTO dto,Pagination page) {
		this.dto = dto;
		this.page = page;
	}
	public SoChildDTO getDto() {
		return dto;
	}
	public void setDto(SoChildDTO dto) {
		this.dto = dto;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	
}
