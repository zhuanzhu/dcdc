package com.egeo.components.config.dto;

import java.util.Date;

import com.egeo.orm.Pagination;

public class KeywordPageDTO {
	private KeywordDTO dto;
	private Pagination page;
	public KeywordDTO getDto() {
		return dto;
	}
	public void setDto(KeywordDTO dto) {
		this.dto = dto;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	
}
