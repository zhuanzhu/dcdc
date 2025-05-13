package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import com.egeo.components.product.po.StandardUnitCombinationPO;


public interface StandardUnitCombinationCategoryWriteManage {

	Long insertStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryPO po);

	int updateStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryPO po);

	int deleteStandardUnitCombinationCategoryWithTx(StandardUnitCombinationCategoryPO po);
	/**
	 * 批量保存su组合和前台类目节点关系
	 * @param standardUnitCombinationId
	 * @param categoryTreeId
	 * @param sortType
	 * @param categoryTreeNodeIdList
	 * @return
	 */
	boolean saveStandardUnitCombinationCategoryAllWithTx(StandardUnitCombinationPO standardUnitCombinationPO, List<Long> categoryTreeNodeIdList);
}
	