package com.egeo.components.product.dto;

import com.egeo.orm.Pagination;

public class CommodityProductUnitPageDTO {
	private CommodityProductUnitDTO dto;
	private Pagination page;
	public CommodityProductUnitPageDTO(CommodityProductUnitDTO dto,Pagination page){
		this.dto = dto;
		this.page = page;
	}
	public CommodityProductUnitDTO getDto() {
		return dto;
	}
	public void setDto(CommodityProductUnitDTO dto) {
		this.dto = dto;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
}
