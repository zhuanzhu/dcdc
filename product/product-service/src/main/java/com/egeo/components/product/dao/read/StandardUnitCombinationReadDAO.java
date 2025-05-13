package com.egeo.components.product.dao.read;

import java.util.List;

import com.egeo.components.product.po.StandardUnitPO;
import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.StandardUnitCombinationPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface StandardUnitCombinationReadDAO extends BaseReadDAO<StandardUnitCombinationPO>{
	/**
	 * 根据标签类型su组合id查询su商品数量
	 * @param id
	 * @return
	 */
	Integer findStandardUnitSizeByTag(@Param("standardUnitCombinationId")Long standardUnitCombinationId,@Param("platformId")Long platformId);

	int countStandardUnitCombinationOfPage(@Param("po")StandardUnitCombinationPO po, @Param("ids")List<Long> standardUnitCombinationIdList);

	List<StandardUnitCombinationPO> findStandardUnitCombinationOfPage(@Param("po")StandardUnitCombinationPO po, @Param("page")Pagination page,
			@Param("ids")List<Long> standardUnitCombinationIdList);

	/**
	 * 模糊查询所有商品组合信息
	 * @param po
	 * @return
	 */
	List<StandardUnitCombinationPO> findAllByBlurry(@Param("po") StandardUnitCombinationPO po , @Param("page") Pagination page);

    int countSuByCategoryIdOfPage(Long platformId, Pagination page, List<Long> categoryTreeNodeIds);

	List<StandardUnitPO> findSuByCategoryIdOfPage(StandardUnitPO po, Pagination page, List<Long> categoryTreeNodeIds);

    List<StandardUnitCombinationPO> findStandardUnitCombinationAllLimit(@Param("suCombinationName")String suCombinationName);
}
	
