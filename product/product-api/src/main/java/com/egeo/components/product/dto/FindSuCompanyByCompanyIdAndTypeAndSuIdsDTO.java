package com.egeo.components.product.dto;

import java.util.List;

public class FindSuCompanyByCompanyIdAndTypeAndSuIdsDTO {
	private StandardUnitCompanyDTO dto;
	private List<Long> suIds;
	public StandardUnitCompanyDTO getDto() {
		return dto;
	}
	public void setDto(StandardUnitCompanyDTO dto) {
		this.dto = dto;
	}
	public List<Long> getSuIds() {
		return suIds;
	}
	public void setSuIds(List<Long> suIds) {
		this.suIds = suIds;
	}
	
}
