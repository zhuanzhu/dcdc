package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsCfgKeyDTO;


public interface CmsCfgKeyWriteService {

	public Long insertCmsCfgKeyWithTx(CmsCfgKeyDTO dto);

	public int updateCmsCfgKeyWithTx(CmsCfgKeyDTO dto);

	public int deleteCmsCfgKeyWithTx(CmsCfgKeyDTO dto);
}
	