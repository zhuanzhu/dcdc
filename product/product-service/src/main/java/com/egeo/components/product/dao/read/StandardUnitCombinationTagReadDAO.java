package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StandardUnitCombinationTagCondition;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;
import com.egeo.orm.BaseReadDAO;

public interface StandardUnitCombinationTagReadDAO extends BaseReadDAO<StandardUnitCombinationTagPO>{
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	List<StandardUnitCombinationTagCondition> findTagByStandardUnitCombinationId(@Param("standardUnitCombinationId")Long standardUnitCombinationId);
}
	