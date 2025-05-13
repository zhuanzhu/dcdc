package com.egeo.components.order.service.write;

import com.egeo.components.order.dto.SoThirdpartyDTO;


public interface SoThirdpartyWriteService {

	public Long insertSoThirdpartyWithTx(SoThirdpartyDTO dto);

	public int updateSoThirdpartyWithTx(SoThirdpartyDTO dto);

	public int deleteSoThirdpartyWithTx(SoThirdpartyDTO dto);

    int updateSoThirdpartyAndSoWithTx(SoThirdpartyDTO soThirdpartyDTO);

    void updateSoThirdpartyByCodeWithTx(SoThirdpartyDTO soThirdpartyDTO);

    void updateSoThirdpartyByThirdIdWithTx(SoThirdpartyDTO thirdpartyDTO);
}
	