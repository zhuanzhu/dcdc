package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;


public interface StandardUnitCombinationTagWriteManage {

	Long insertStandardUnitCombinationTagWithTx(StandardUnitCombinationTagPO po);

	int updateStandardUnitCombinationTagWithTx(StandardUnitCombinationTagPO po);

	int deleteStandardUnitCombinationTagWithTx(StandardUnitCombinationTagPO po);
	/**
	 * 批量保存su组合id与标签关系信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	Integer saveStandardUnitCombinationTagWithTx(StandardUnitCombinationPO po, List<Long> tagIdList);
	/**
	 * 根据标签id清除记录
	 * @param tagId
	 * @return
	 */
	int delByTagId(Long tagId);
}
	