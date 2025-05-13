package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ElementDictWriteService;
import com.egeo.components.cms.manage.write.ElementDictWriteManage;
import com.egeo.components.cms.converter.ElementDictConverter;
import com.egeo.components.cms.dto.ElementDictDTO;
import com.egeo.components.cms.po.ElementDictPO;

@Service("elementDictWriteService")
public class ElementDictWriteServiceImpl  implements ElementDictWriteService {
	@Autowired
	private ElementDictWriteManage elementDictWriteManage;

	@Override
	public Long insertElementDictWithTx(ElementDictDTO dto) {
		ElementDictPO po = ElementDictConverter.toPO(dto);
		Long rt = elementDictWriteManage.insertElementDictWithTx(po);		
		return rt;
	}

	@Override
	public int updateElementDictWithTx(ElementDictDTO dto) {
		ElementDictPO po = ElementDictConverter.toPO(dto);
		int rt = elementDictWriteManage.updateElementDictWithTx(po);		
		return rt;
	}

	@Override
	public int deleteElementDictWithTx(ElementDictDTO dto) {
		ElementDictPO po = ElementDictConverter.toPO(dto);
		int rt = elementDictWriteManage.deleteElementDictWithTx(po);		
		return rt;
	}
}
	