package com.egeo.components.product.dto;

import com.egeo.orm.Pagination;

public class StandardUnitPageDTO {
	private StandardUnitDTO dto;
	private Pagination page;
	public StandardUnitPageDTO(StandardUnitDTO dto,Pagination page) {
		this.dto = dto;
		this.page = page;
	}
	public StandardUnitDTO getDto() {
		return dto;
	}
	public void setDto(StandardUnitDTO dto) {
		this.dto = dto;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
}
