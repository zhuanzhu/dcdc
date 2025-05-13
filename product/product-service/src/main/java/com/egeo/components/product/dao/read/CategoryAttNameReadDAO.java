package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.CategoryAttNameCondition;
import com.egeo.components.product.po.CategoryAttNamePO;
import com.egeo.components.product.po.CategoryTreeNodePO;
import com.egeo.orm.BaseReadDAO;

public interface CategoryAttNameReadDAO extends BaseReadDAO<CategoryAttNamePO>{
    List<CategoryAttNameCondition> categoryAttNameByCategoryId(@Param("po")CategoryAttNamePO po);

    CategoryAttNamePO categoryAttNameByAttNameId(@Param("po")CategoryAttNamePO po);

    List<CategoryAttNamePO> categoryAttNameByCId(@Param("po")CategoryAttNamePO po);

    List<CategoryAttNamePO> getcategoryAttNameListByNodeId(@Param("po") CategoryTreeNodePO po);

    List<CategoryAttNamePO> merchantProductAttNameByCId(@Param("po") CategoryAttNamePO po);
    /**
     * 根据类目id和属性id查询类目属性是否必填写 0否、1是
     * @param categoryId
     * @param attNameId
     * @return
     */
	int isRequiredByCategoryIdAttNameId(@Param("categoryId")Long categoryId, @Param("attNameId")Long attNameId);
	/**
	 * 根据类目id查询类目规格数量
	 * @param categoryId
	 * @return
	 */
	int findSumByCategoryId(@Param("categoryId")Long categoryId);
}
	