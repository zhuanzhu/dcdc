package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.CmsDictDTO;


public interface CmsDictWriteService {

	public Long insertCmsDictWithTx(CmsDictDTO dto);

	public int updateCmsDictWithTx(CmsDictDTO dto);

	public int deleteCmsDictWithTx(CmsDictDTO dto);
}
	