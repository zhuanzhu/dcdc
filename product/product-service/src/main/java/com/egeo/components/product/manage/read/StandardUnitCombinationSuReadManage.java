package com.egeo.components.product.manage.read;

import java.util.Date;
import java.util.List;

import com.egeo.components.product.condition.StandardUnitCombinationSuCondition;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitCombinationSuReadManage {

	public StandardUnitCombinationSuPO findStandardUnitCombinationSuById(StandardUnitCombinationSuPO po);

	public PageResult<StandardUnitCombinationSuPO> findStandardUnitCombinationSuOfPage(StandardUnitCombinationSuPO po,Pagination page);

	public List<StandardUnitCombinationSuPO> findStandardUnitCombinationSuAll(StandardUnitCombinationSuPO po);
	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	public PageResult<StandardUnitCombinationSuCondition> findByStandardUnitCombinationIdOfPage(
			Long standardUnitCombinationId, Pagination page);
	/**
	 * 根据su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSize(Long standardUnitCombinationId);

	List<StandardUnitCombinationSuPO> syncJdSellState(Integer source,Date endCheckTime,int size);
}
