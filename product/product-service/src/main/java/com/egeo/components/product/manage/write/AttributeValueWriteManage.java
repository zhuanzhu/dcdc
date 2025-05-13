package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.AttributeValuePO;

import java.util.List;

public interface AttributeValueWriteManage {

    Long saveAttributeValue(AttributeValuePO po);

	Integer deleteByIdWithTx(AttributeValuePO po);

	Long updateAttributeValueWithTx(AttributeValuePO po);
	/**
	 * 根据属性id删除属性值信息(ps：因为主键唯一无须平台id)
	 * @param id
	 * @return
	 */
	int deleteByAttributeNameIdWithTx(Long attributeNameId);

    void saveAttValue(List<AttributeValuePO> attributeValuePOList);

    void updateAttributeValueList(List<AttributeValuePO> attributeValuePOList);
}
	