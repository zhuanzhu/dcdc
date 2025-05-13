package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.AttributeNameDecimalPO;


public interface AttributeNameDecimalWriteManage {

	Long insertAttributeNameDecimalWithTx(AttributeNameDecimalPO po);

	int updateAttributeNameDecimalWithTx(AttributeNameDecimalPO po);

	int deleteAttributeNameDecimalWithTx(AttributeNameDecimalPO po);
	/**
	 * 根据属性id更新属性范围信息
	 * @param attributeNameDecimalDTO
	 * @return
	 */
	int updateAttributeNameDecimalByAttNameIdWithTx(AttributeNameDecimalPO po);
}
	