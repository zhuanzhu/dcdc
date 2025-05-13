package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ElementDictReadService;
import com.egeo.components.cms.manage.read.ElementDictReadManage;
import com.egeo.components.cms.converter.ElementDictConverter;
import com.egeo.components.cms.dto.ElementDictDTO;
import com.egeo.components.cms.po.ElementDictPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("elementDictReadService")
public class ElementDictReadServiceImpl  implements ElementDictReadService {
	@Autowired
	private ElementDictReadManage elementDictReadManage;

	@Override
	public ElementDictDTO findElementDictById(Long elementDictId) {
		ElementDictPO po = new ElementDictPO();
		po.setId(elementDictId);
		ElementDictPO list = elementDictReadManage.findElementDictById(po);		
		return ElementDictConverter.toDTO(list);
	}

	@Override
	public PageResult<ElementDictDTO> findElementDictOfPage(ElementDictDTO dto, Pagination page) {
		ElementDictPO po = ElementDictConverter.toPO(dto);
        PageResult<ElementDictPO> pageResult = elementDictReadManage.findElementDictOfPage(po, page);
        
        List<ElementDictDTO> list = ElementDictConverter.toDTO(pageResult.getList());
        PageResult<ElementDictDTO> result = new PageResult<ElementDictDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ElementDictDTO> findElementDictAll(ElementDictDTO dto) {
		ElementDictPO po = ElementDictConverter.toPO(dto);
		List<ElementDictPO> list = elementDictReadManage.findElementDictAll(po);		
		return ElementDictConverter.toDTO(list);
	}

	@Override
	public ElementDictDTO queryElementDictByConfigType(Integer configType) {
		return ElementDictConverter.toDTO(elementDictReadManage.queryElementDictByConfigType(configType));
	}

	@Override
	public List<ElementDictDTO> queryElementDictByNotType(Integer type) {
		return ElementDictConverter.toDTO(elementDictReadManage.queryElementDictByNotType(type));
	}
}
	