package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.AttributeNameDecimalReadService;
import com.egeo.components.product.manage.read.AttributeNameDecimalReadManage;
import com.egeo.components.product.converter.AttributeNameDecimalConverter;
import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.components.product.po.AttributeNameDecimalPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("attributeNameDecimalReadService")
public class AttributeNameDecimalReadServiceImpl  implements AttributeNameDecimalReadService {
	@Autowired
	private AttributeNameDecimalReadManage attributeNameDecimalReadManage;

	@Override
	public AttributeNameDecimalDTO findAttributeNameDecimalById(AttributeNameDecimalDTO dto) {
		AttributeNameDecimalPO po = AttributeNameDecimalConverter.toPO(dto);
		AttributeNameDecimalPO list = attributeNameDecimalReadManage.findAttributeNameDecimalById(po);		
		return AttributeNameDecimalConverter.toDTO(list);
	}

	@Override
	public PageResult<AttributeNameDecimalDTO> findAttributeNameDecimalOfPage(AttributeNameDecimalDTO dto, Pagination page) {
		AttributeNameDecimalPO po = AttributeNameDecimalConverter.toPO(dto);
        PageResult<AttributeNameDecimalPO> pageResult = attributeNameDecimalReadManage.findAttributeNameDecimalOfPage(po, page);
        
        List<AttributeNameDecimalDTO> list = AttributeNameDecimalConverter.toDTO(pageResult.getList());
        PageResult<AttributeNameDecimalDTO> result = new PageResult<AttributeNameDecimalDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AttributeNameDecimalDTO> findAttributeNameDecimalAll(AttributeNameDecimalDTO dto) {
		AttributeNameDecimalPO po = AttributeNameDecimalConverter.toPO(dto);
		List<AttributeNameDecimalPO> list = attributeNameDecimalReadManage.findAttributeNameDecimalAll(po);		
		return AttributeNameDecimalConverter.toDTO(list);
	}
}
	