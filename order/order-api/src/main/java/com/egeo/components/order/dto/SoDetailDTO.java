package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.List;


public class SoDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private SoDTO soDTO;
	private List<SoItemDTO> SoItemDTOList;

	public SoDTO getSoDTO() {
		return soDTO;
	}

	public void setSoDTO(SoDTO soDTO) {
		this.soDTO = soDTO;
	}

	public List<SoItemDTO> getSoItemDTOList() {
		return SoItemDTOList;
	}

	public void setSoItemDTOList(List<SoItemDTO> soItemDTOList) {
		SoItemDTOList = soItemDTOList;
	}

}
