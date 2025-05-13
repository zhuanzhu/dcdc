package com.egeo.components.product.dao.write;

import com.egeo.components.product.po.AttributeNameDecimalPO;
import com.egeo.orm.BaseWriteDAO;

public interface AttributeNameDecimalWriteDAO extends BaseWriteDAO<AttributeNameDecimalPO> {
	/**
	 * 根据属性id更新属性范围信息
	 * @param attributeNameDecimalDTO
	 * @return
	 */
	int updateAttributeNameDecimalByAttNameIdWithTx(AttributeNameDecimalPO po);
}
	