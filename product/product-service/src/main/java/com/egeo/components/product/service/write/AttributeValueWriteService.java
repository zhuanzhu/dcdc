package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.AttributeValueDTO;

import java.util.List;

public interface AttributeValueWriteService {

    Long saveAttributeValueWithTx(AttributeValueDTO dto);

	Integer deleteByIdWithTx(AttributeValueDTO dto);

	Long updateAttributeValueWithTx(AttributeValueDTO attributeValueDTO2);
	/**
	 * 根据属性id删除属性值信息(ps：因为主键唯一无须平台id)
	 * @param id
	 * @return
	 */
	int deleteByAttributeNameIdWithTx(Long attributeNameId);

    void saveAttValue(List<Long> attValueIdList, List<String> attValueList);

    void updateAttributeValueList(List<Long> attValueIdList, List<String> attValueList);
}
	