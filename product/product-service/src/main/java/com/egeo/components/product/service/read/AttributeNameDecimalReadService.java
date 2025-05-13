package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface AttributeNameDecimalReadService {

	public AttributeNameDecimalDTO findAttributeNameDecimalById(AttributeNameDecimalDTO dto);

	public PageResult<AttributeNameDecimalDTO> findAttributeNameDecimalOfPage(AttributeNameDecimalDTO dto,Pagination page);

	public List<AttributeNameDecimalDTO> findAttributeNameDecimalAll(AttributeNameDecimalDTO dto);
}
	