package com.egeo.components.product.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitDescribePO;
import com.egeo.orm.BaseReadDAO;

public interface StandardUnitDescribeReadDAO extends BaseReadDAO<StandardUnitDescribePO>{
	/**
	 * 根据su商品id查询su商品星详情
	 * @param standardUnitId
	 * @return
	 */
	String findContentByStandardUnitId(@Param("standardUnitId")Long standardUnitId);
}
	