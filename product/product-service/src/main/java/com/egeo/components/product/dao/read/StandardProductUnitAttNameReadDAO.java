package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.StandardProductUnitAttNameCondition;
import com.egeo.components.product.po.StandardProductUnitAttNamePO;
import com.egeo.orm.BaseReadDAO;

public interface StandardProductUnitAttNameReadDAO extends BaseReadDAO<StandardProductUnitAttNamePO>{
	/**
	 * 根据spuid和除属性id为的所有spu属性信息
	 * @param standardProductUnitId
	 * @param attNameId
	 * @return
	 */
	List<StandardProductUnitAttNamePO> findByStandardProductUnitIdAttNameId(@Param("standardProductUnitId")Long standardProductUnitId, @Param("attNameId")Long attNameId);
	/**
	 * 根据suid查询su参数属性
	 * @param standardProductUnitId
	 * @return
	 */
	List<StandardProductUnitAttNameCondition> findByStandardProductUnitId(@Param("standardProductUnitId")Long standardProductUnitId);
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	List<StandardProductUnitAttNameCondition> findByStandardUnitId(@Param("standardUnitId")Long standardUnitId);
	/**
	 * 根据spuId查询spu属性信息
	 * @param standardProductUnitId
	 * @return
	 */
	List<StandardProductUnitAttNameCondition> findStandardProductUnitAttNameAll(@Param("standardProductUnitId")Long standardProductUnitId);

    Long findLastId();
}
	