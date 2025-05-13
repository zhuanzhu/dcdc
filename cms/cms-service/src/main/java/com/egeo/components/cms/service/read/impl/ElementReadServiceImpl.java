package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ElementReadService;
import com.egeo.components.cms.manage.read.ElementReadManage;
import com.egeo.components.cms.converter.ElementConverter;
import com.egeo.components.cms.dto.ElementDTO;
import com.egeo.components.cms.po.ElementPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("elementReadService")
public class ElementReadServiceImpl  implements ElementReadService {
	@Autowired
	private ElementReadManage elementReadManage;

	@Override
	public ElementDTO findElementById(Long id) {
		ElementPO po = new ElementPO();
		po.setId(id);
		ElementPO list = elementReadManage.findElementById(po);		
		return ElementConverter.toDTO(list);
	}

	@Override
	public PageResult<ElementDTO> findElementOfPage(ElementDTO dto, Pagination page) {
		ElementPO po = ElementConverter.toPO(dto);
        PageResult<ElementPO> pageResult = elementReadManage.findElementOfPage(po, page);
        
        List<ElementDTO> list = ElementConverter.toDTO(pageResult.getList());
        PageResult<ElementDTO> result = new PageResult<ElementDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ElementDTO> findElementAll(ElementDTO dto) {
		ElementPO po = ElementConverter.toPO(dto);
		List<ElementPO> list = elementReadManage.findElementAll(po);		
		return ElementConverter.toDTO(list);
	}

	@Override
	public List<ElementDTO> queryElementListByTmlpId(Long templateId) {
		return ElementConverter.toDTO(elementReadManage.queryElementListByTmlpId(templateId));
	}

	@Override
	public List<ElementDTO> queryElementListByTmlpIdByPage(Long id, Pagination page) {
		return ElementConverter.toDTO(elementReadManage.queryElementListByTmlpIdByPage(id,page));
	}
}
	