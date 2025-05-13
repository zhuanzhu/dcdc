package com.egeo.components.user.dto;

import com.egeo.orm.Pagination;

public class UserExtendPageDTO {
	private Pagination page;
	private UserExtendDTO dto;
	public UserExtendPageDTO(Pagination page,UserExtendDTO dto) {
		this.page = page;
		this.dto = dto;
	}
	public UserExtendPageDTO() {
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public UserExtendDTO getDto() {
		return dto;
	}
	public void setDto(UserExtendDTO dto) {
		this.dto = dto;
	}
}
