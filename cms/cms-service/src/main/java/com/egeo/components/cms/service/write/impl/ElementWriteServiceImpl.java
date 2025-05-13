package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ElementWriteService;
import com.egeo.components.cms.manage.write.ElementWriteManage;
import com.egeo.components.cms.converter.ElementConverter;
import com.egeo.components.cms.dto.ElementDTO;
import com.egeo.components.cms.po.ElementPO;

@Service("elementWriteService")
public class ElementWriteServiceImpl  implements ElementWriteService {
	@Autowired
	private ElementWriteManage elementWriteManage;

	@Override
	public Long insertElementWithTx(ElementDTO dto) {
		ElementPO po = ElementConverter.toPO(dto);
		Long rt = elementWriteManage.insertElementWithTx(po);		
		return rt;
	}

	@Override
	public int updateElementWithTx(ElementDTO dto) {
		ElementPO po = ElementConverter.toPO(dto);
		int rt = elementWriteManage.updateElementWithTx(po);		
		return rt;
	}

	@Override
	public int deleteElementWithTx(ElementDTO dto) {
		ElementPO po = ElementConverter.toPO(dto);
		int rt = elementWriteManage.deleteElementWithTx(po);		
		return rt;
	}

	@Override
	public int deleteElementById(Long elementId) {
		ElementPO po =new ElementPO();
		po.setId(elementId);
		return elementWriteManage.deleteElementWithTx(po);
	}
}
	