package com.egeo.components.product.manage.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StandardUnitCombinationTagCondition;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCombinationTagReadManage {

	public StandardUnitCombinationTagPO findStandardUnitCombinationTagById(StandardUnitCombinationTagPO po);

	public PageResult<StandardUnitCombinationTagPO> findStandardUnitCombinationTagOfPage(StandardUnitCombinationTagPO po,Pagination page);

	public List<StandardUnitCombinationTagPO> findStandardUnitCombinationTagAll(StandardUnitCombinationTagPO po);
	/**
	 * 根据su组合id查询su组合标签信息
	 * @param standardUnitCombinationId
	 * @return
	 */
	public List<StandardUnitCombinationTagCondition> findTagByStandardUnitCombinationId(@Param("standardUnitCombinationId")Long standardUnitCombinationId);
}
	