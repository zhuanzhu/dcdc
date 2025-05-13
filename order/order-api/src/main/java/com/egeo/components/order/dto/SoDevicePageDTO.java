package com.egeo.components.order.dto;

import com.egeo.orm.Pagination;

public class SoDevicePageDTO {
	private SoDeviceDTO dto;
	private Pagination page;
	public SoDeviceDTO getDto() {
		return dto;
	}
	public void setDto(SoDeviceDTO dto) {
		this.dto = dto;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
}
