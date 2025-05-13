package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.AttributeNameDecimalManage;
import com.egeo.components.product.facade.AttributeNameDecimalFacade;
import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("attributeNameDecimal")
public class AttributeNameDecimalManageImpl implements AttributeNameDecimalManage{

	
	@Resource(name="attributeNameDecimalFacade")
	private AttributeNameDecimalFacade attributeNameDecimalFacade;

	@Override
	public AttributeNameDecimalDTO findAttributeNameDecimalById(AttributeNameDecimalDTO dto) {
		return attributeNameDecimalFacade.findAttributeNameDecimalById(dto);
	}

	@Override
	public PageResult<AttributeNameDecimalDTO> findAttributeNameDecimalOfPage(AttributeNameDecimalDTO dto, Pagination page) {
		return attributeNameDecimalFacade.findAttributeNameDecimalOfPage(dto, page);
	}

	@Override
	public List<AttributeNameDecimalDTO> findAttributeNameDecimalAll(AttributeNameDecimalDTO dto) {
		return attributeNameDecimalFacade.findAttributeNameDecimalAll(dto);
	}

	@Override
	public Long insertAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto) {
		return attributeNameDecimalFacade.insertAttributeNameDecimalWithTx(dto);
	}

	@Override
	public int updateAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto) {
		return attributeNameDecimalFacade.updateAttributeNameDecimalWithTx(dto);
	}

	@Override
	public int deleteAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto) {
		return attributeNameDecimalFacade.deleteAttributeNameDecimalWithTx(dto);
	}


}
	