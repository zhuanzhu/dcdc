package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsCfgValueDTO;


public interface CmsCfgValueWriteService {

	public Long insertCmsCfgValueWithTx(CmsCfgValueDTO dto);

	public int updateCmsCfgValueWithTx(CmsCfgValueDTO dto);

	public int deleteCmsCfgValueWithTx(CmsCfgValueDTO dto);
}
	