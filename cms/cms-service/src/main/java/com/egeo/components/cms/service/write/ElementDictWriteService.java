package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ElementDictDTO;


public interface ElementDictWriteService {

	public Long insertElementDictWithTx(ElementDictDTO dto);

	public int updateElementDictWithTx(ElementDictDTO dto);

	public int deleteElementDictWithTx(ElementDictDTO dto);
}
	