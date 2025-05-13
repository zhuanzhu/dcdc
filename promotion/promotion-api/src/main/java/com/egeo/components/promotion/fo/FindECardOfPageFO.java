package com.egeo.components.promotion.fo;

import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.orm.Pagination;

public class FindECardOfPageFO {
	private ECardDTO dto;
	private Pagination page;
	public FindECardOfPageFO(ECardDTO dto,Pagination page) {
		this.dto = dto;
		this.page = page;
	}
	public ECardDTO getDto() {
		return dto;
	}
	public void setDto(ECardDTO dto) {
		this.dto = dto;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
}
