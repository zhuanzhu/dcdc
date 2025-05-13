package com.egeo.components.product.manage.write;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.po.StandardUnitCombinationSuPO;


public interface StandardUnitCombinationSuWriteManage {

	Long insertStandardUnitCombinationSuWithTx(StandardUnitCombinationSuPO po);

	int updateStandardUnitCombinationSuWithTx(StandardUnitCombinationSuPO po);

	int deleteStandardUnitCombinationSuWithTx(StandardUnitCombinationSuPO po);
	/**
	 * 批量保存su组合和su商品关系
	 * @param standardUnitCombinationId
	 * @param standardUnitList
	 * @return
	 */
	boolean saveStandardUnitCombinationSuAllWithTx(Long standardUnitCombinationId, List<Long> standardUnits,Integer source,List<Map<String,String>> productAndSkuMap);
	/**
	 * 根据su组合id修改排序
	 * @param standardUnitCombinationId
	 * @param sortValue
	 * @return
	 */
	boolean sortValueByStandardUnitCombinationIdWithTx(Long standardUnitCombinationId, Integer sortValue);
}
