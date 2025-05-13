package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.AttributeNameDecimalWriteService;
import com.egeo.components.product.manage.write.AttributeNameDecimalWriteManage;
import com.egeo.components.product.converter.AttributeNameDecimalConverter;
import com.egeo.components.product.dto.AttributeNameDecimalDTO;
import com.egeo.components.product.po.AttributeNameDecimalPO;

@Service("attributeNameDecimalWriteService")
public class AttributeNameDecimalWriteServiceImpl  implements AttributeNameDecimalWriteService {
	@Autowired
	private AttributeNameDecimalWriteManage attributeNameDecimalWriteManage;

	@Override
	public Long insertAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto) {
		AttributeNameDecimalPO po = AttributeNameDecimalConverter.toPO(dto);
		Long rt = attributeNameDecimalWriteManage.insertAttributeNameDecimalWithTx(po);		
		return rt;
	}

	@Override
	public int updateAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto) {
		AttributeNameDecimalPO po = AttributeNameDecimalConverter.toPO(dto);
		int rt = attributeNameDecimalWriteManage.updateAttributeNameDecimalWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAttributeNameDecimalWithTx(AttributeNameDecimalDTO dto) {
		AttributeNameDecimalPO po = AttributeNameDecimalConverter.toPO(dto);
		int rt = attributeNameDecimalWriteManage.deleteAttributeNameDecimalWithTx(po);		
		return rt;
	}
	/**
	 * 根据属性id更新属性范围信息
	 * @param attributeNameDecimalDTO
	 * @return
	 */
	@Override
	public int updateAttributeNameDecimalByAttNameIdWithTx(AttributeNameDecimalDTO attributeNameDecimalDTO) {
		// TODO Auto-generated method stub
		return attributeNameDecimalWriteManage.updateAttributeNameDecimalByAttNameIdWithTx(AttributeNameDecimalConverter.toPO(attributeNameDecimalDTO));
	}
}
	