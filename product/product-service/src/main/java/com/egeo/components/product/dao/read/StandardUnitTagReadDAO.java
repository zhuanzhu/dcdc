package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitTagPO;
import com.egeo.orm.BaseReadDAO;

public interface StandardUnitTagReadDAO extends BaseReadDAO<StandardUnitTagPO>{
	/**
	 * 根据suId查询su标签信息
	 * @param suId
	 * @return
	 */
	List<String> findStandardUnitTagBySuId(@Param("suId")Long suId);
}
	