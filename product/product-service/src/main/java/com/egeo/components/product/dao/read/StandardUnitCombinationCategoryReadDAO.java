package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import com.egeo.orm.BaseReadDAO;

public interface StandardUnitCombinationCategoryReadDAO extends BaseReadDAO<StandardUnitCombinationCategoryPO>{
	/**
	 * 根据类目类型su组合id查询su商品数量
	 * @param standardUnitCombinationId
	 * @return
	 */
	Integer findStandardUnitSizeByCategoryList(@Param("ids")List<Long> newList);
	/**
	 * 根据类目类型su组合id查询su商品数量
	 * @param standardUnitCombinationId
	 * @return
	 */
	Integer findStandardUnitSizeByCategoryType(@Param("standardUnitCombinationId")Long standardUnitCombinationId);
}
	