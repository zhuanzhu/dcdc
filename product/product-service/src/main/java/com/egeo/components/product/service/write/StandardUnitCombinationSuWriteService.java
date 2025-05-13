package com.egeo.components.product.service.write;

import java.util.List;
import java.util.Map;

import com.egeo.components.product.dto.StandardUnitCombinationSuDTO;


public interface StandardUnitCombinationSuWriteService {

	public Long insertStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto);

	public int updateStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto);

	public int deleteStandardUnitCombinationSuWithTx(StandardUnitCombinationSuDTO dto);
	/**
	 * 批量保存su组合和su商品关系
	 * @param standardUnitCombinationId
	 * @param standardUnitList
	 * @return
	 */
	public boolean saveStandardUnitCombinationSuAllWithTx(Long standardUnitCombinationId, List<Long> standardUnits,Integer source,List<Map<String,String>> productAndSkuMap);
	/**
	 * 根据su组合与su关系id修改排序
	 * @param standardUnitCombinationId
	 * @param sortValue
	 * @return
	 */
	public boolean sortValueByStandardUnitCombinationIdWithTx(Long standardUnitCombinationSuId, Integer sortValue);
}
