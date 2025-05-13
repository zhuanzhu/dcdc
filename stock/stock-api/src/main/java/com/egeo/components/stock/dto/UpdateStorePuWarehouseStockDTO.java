package com.egeo.components.stock.dto;

import java.util.List;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;


public class UpdateStorePuWarehouseStockDTO {
	private List<SoItemDTO> soItems;
	private Integer type;
	private SoDTO sodto;
	public UpdateStorePuWarehouseStockDTO() {}
	public UpdateStorePuWarehouseStockDTO(Integer type,SoDTO sodto, List<SoItemDTO> soItems) {
		this.soItems = soItems;
		this.type = type;
		this.sodto = sodto;
	}
	public List<SoItemDTO> getSoItems() {
		return soItems;
	}
	public void setSoItems(List<SoItemDTO> soItems) {
		this.soItems = soItems;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public SoDTO getSodto() {
		return sodto;
	}
	public void setSodto(SoDTO sodto) {
		this.sodto = sodto;
	}
	
}
