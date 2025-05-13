package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoPackageBoxDTO;


public interface SoPackageBoxWriteService {

	public Long insertSoPackageBoxWithTx(SoPackageBoxDTO dto);

	public int updateSoPackageBoxWithTx(SoPackageBoxDTO dto);

	public int deleteSoPackageBoxWithTx(SoPackageBoxDTO dto);
}
	