package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.StandardProductUnitAttNameCondition;
import com.egeo.components.product.po.StandardProductUnitAttNamePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitAttNameReadManage {

	public StandardProductUnitAttNamePO findStandardProductUnitAttNameById(StandardProductUnitAttNamePO po);

	public PageResult<StandardProductUnitAttNamePO> findStandardProductUnitAttNameOfPage(StandardProductUnitAttNamePO po,Pagination page);

	public List<StandardProductUnitAttNamePO> findStandardProductUnitAttNameAll(StandardProductUnitAttNamePO po);
	/**
	 * 根据spuid和除属性id为的所有spu属性信息
	 * @param standardProductUnitId
	 * @param attNameId
	 * @return
	 */
	public List<StandardProductUnitAttNamePO> findByStandardProductUnitIdAttNameId(Long standardProductUnitId,
			Long attNameId);
	/**
	 * 根据spuid查询su参数属性
	 * @param standardProductUnitId
	 * @return
	 */
	public List<StandardProductUnitAttNameCondition> findByStandardProductUnitId(Long standardProductUnitId);
	/**
	 * 根据suid查询spu规格属性
	 * @param standardUnitId
	 * @param req
	 * @return
	 */
	public List<StandardProductUnitAttNameCondition> findByStandardUnitId(Long standardUnitId);
	/**
	 * 根据spuId查询spu属性信息
	 * @param standardProductUnitId
	 * @return
	 */
	public List<StandardProductUnitAttNameCondition> findStandardProductUnitAttNameAll(Long standardProductUnitId);

    Long findLastId();
}
	