package com.egeo.components.finance.bean;

import java.util.List;

import com.egeo.components.order.dto.SoDTO;
import com.egeo.components.order.dto.SoItemDTO;

public class SoDataDTO {
	private SoDTO order;
	private List<SoItemDTO> items;
	private Long enterpriseId;
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public SoDTO getOrder() {
		return order;
	}
	public void setOrder(SoDTO order) {
		this.order = order;
	}
	public List<SoItemDTO> getItems() {
		return items;
	}
	public void setItems(List<SoItemDTO> items) {
		this.items = items;
	}
	
}
