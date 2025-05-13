package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoPackageItemDTO;


public interface SoPackageItemWriteService {

	public int insertSoPackageItemWithTx(SoPackageItemDTO dto);

	public int updateSoPackageItemWithTx(SoPackageItemDTO dto);

	public int deleteSoPackageItemWithTx(SoPackageItemDTO dto);
}
	