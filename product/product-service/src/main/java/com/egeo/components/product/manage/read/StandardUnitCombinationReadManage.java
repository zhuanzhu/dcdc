package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCombinationReadManage {

	public StandardUnitCombinationPO findStandardUnitCombinationById(StandardUnitCombinationPO po);

	public PageResult<StandardUnitCombinationPO> findStandardUnitCombinationOfPage(StandardUnitCombinationPO po,Pagination page,List<Long> standardUnitCombinationIdList);

	public List<StandardUnitCombinationPO> findStandardUnitCombinationAll(StandardUnitCombinationPO po);
	/**
	 * 根据标签类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSizeByTag(Long standardUnitCombinationId,Long platformId);

	/**
	 * 模糊查询所有商品组合信息
	 * @param po
	 * @return
	 */
	public List<StandardUnitCombinationPO> findStandardUnitCombinationAllByBlurry(StandardUnitCombinationPO po);

    List<StandardUnitCombinationPO> findStandardUnitCombinationAllLimit(String suCombinationName);
}
	