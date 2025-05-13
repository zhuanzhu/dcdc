package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.AttributeNameDecimalDTO;


public interface AttributeNameDecimalWriteService {

	public Long insertAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto);

	public int updateAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto);

	public int deleteAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto);
	/**
	 * 根据属性id更新属性范围信息
	 * @param attributeNameDecimalDTO
	 * @return
	 */
	public int updateAttributeNameDecimalByAttNameIdWithTx(AttributeNameDecimalDTO attributeNameDecimalDTO);
}
	