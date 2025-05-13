package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface AttributeNameDecimalManage {

	public AttributeNameDecimalDTO findAttributeNameDecimalById(AttributeNameDecimalDTO dto);	

	public PageResult<AttributeNameDecimalDTO> findAttributeNameDecimalOfPage(AttributeNameDecimalDTO dto,Pagination page);

	public List<AttributeNameDecimalDTO> findAttributeNameDecimalAll(AttributeNameDecimalDTO dto);

	Long insertAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto);

	int updateAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto);

	int deleteAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto);
}
	