package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.StandardUnitCombinationCategoryPO;
import com.egeo.components.product.po.StandardUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCombinationCategoryReadManage {

	public StandardUnitCombinationCategoryPO findStandardUnitCombinationCategoryById(StandardUnitCombinationCategoryPO po);

	public PageResult<StandardUnitCombinationCategoryPO> findStandardUnitCombinationCategoryOfPage(StandardUnitCombinationCategoryPO po,Pagination page);

	public List<StandardUnitCombinationCategoryPO> findStandardUnitCombinationCategoryAll(StandardUnitCombinationCategoryPO po);
	/**
	 * 根据类目类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	public Integer findStandardUnitSizeByCategoryType(Long standardUnitCombinationId);
	/**
	 * 根据类目类型su组合id查询su商品列表
	 * @param standardUnitCombinationId
	 * @return
	 */
	public PageResult<StandardUnitPO> findByStandardUnitCombinationIdOfPage(Long standardUnitCombinationId,
			Pagination page);
}
	