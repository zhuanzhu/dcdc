package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.StandardProductUnitAttValuePO;

import java.util.List;


public interface StandardProductUnitAttValueWriteManage {

	Long insertStandardProductUnitAttValueWithTx(StandardProductUnitAttValuePO po);

	int updateStandardProductUnitAttValueWithTx(StandardProductUnitAttValuePO po);

	int deleteStandardProductUnitAttValueWithTx(StandardProductUnitAttValuePO po);
	/**
	 * 根据spu属性id删除spu属性值信息
	 * @param id
	 * @return
	 */
	int deleteByStandardProductUnitAttNameId(Long standardProductUnitAttNameId);

    void saveSPUValue(List<StandardProductUnitAttValuePO> standardProductUnitAttValuePOList);
}
	