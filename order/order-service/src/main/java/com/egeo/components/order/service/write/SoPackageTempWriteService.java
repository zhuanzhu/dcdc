package com.egeo.components.order.service.write;

import java.util.List;

import com.egeo.components.order.dto.SoPackageTempDTO;


public interface SoPackageTempWriteService {

	public Long insertSoPackageTempWithTx(SoPackageTempDTO dto);

	public int updateSoPackageTempWithTx(SoPackageTempDTO dto);

	public int deleteSoPackageTempWithTx(SoPackageTempDTO dto);

	public String insertSoPackageTempListWithTx(List<SoPackageTempDTO> dtoList);
}
	