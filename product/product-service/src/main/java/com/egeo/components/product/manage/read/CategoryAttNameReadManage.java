package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.CategoryAttNameCondition;
import com.egeo.components.product.po.CategoryAttNamePO;

public interface CategoryAttNameReadManage {

    List<CategoryAttNameCondition> categoryAttNameByCategoryId(CategoryAttNamePO po);

    CategoryAttNamePO categoryAttNameByAttNameId(CategoryAttNamePO po);

    CategoryAttNamePO findById(CategoryAttNamePO po);

    List<CategoryAttNamePO> findAll(CategoryAttNamePO po);

    List<CategoryAttNamePO> categoryAttNameByCId(CategoryAttNamePO po);

    List<CategoryAttNamePO> merchantProductAttNameByCId(CategoryAttNamePO po);
    /**
     * 根据类目id和属性id查询类目属性是否必填写 0否、1是
     * @param categoryId
     * @param attNameId
     * @return
     */
	int isRequiredByCategoryIdAttNameId(Long categoryId, Long attNameId);
}
	