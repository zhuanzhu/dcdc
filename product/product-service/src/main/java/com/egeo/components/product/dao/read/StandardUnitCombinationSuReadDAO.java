package com.egeo.components.product.dao.read;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StandardUnitCombinationSuCondition;
import com.egeo.components.product.po.StandardUnitCombinationSuPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface StandardUnitCombinationSuReadDAO extends BaseReadDAO<StandardUnitCombinationSuPO>{
	/**
	 * 根据su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	List<StandardUnitCombinationSuCondition> findByStandardUnitCombinationIdOfPage(@Param("standardUnitCombinationId")Long standardUnitCombinationId,
			@Param("page")Pagination page);
	/**
	 * 根据su组合id查询su商品条数
	 * @param standardUnitCombinationId
	 * @return
	 */
	int countByStandardUnitCombinationIdOfPage(@Param("standardUnitCombinationId")Long standardUnitCombinationId);
	/**
	 * 根据su组合id查询su商品排序最大值
	 * @param id
	 * @return
	 */
	int findStandardUnitNumberMax(@Param("standardUnitCombinationId")Long standardUnitCombinationId);
	/**
	 * 根据su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	Integer findStandardUnitSize(@Param("standardUnitCombinationId")Long standardUnitCombinationId);

	List<StandardUnitCombinationSuPO> syncJdSellState(@Param("source")Integer source, @Param("endCheckTime")Date endCheckTime,
															 @Param("size")int size);
}
	