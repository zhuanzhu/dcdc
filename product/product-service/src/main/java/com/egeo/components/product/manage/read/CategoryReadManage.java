package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.CategoryCondition;
import com.egeo.components.product.po.CategoryPO;

public interface CategoryReadManage {

    List<CategoryCondition> findAll(CategoryPO po);

    CategoryPO findById(CategoryPO categoryPO);

    List<CategoryPO> findAllList(CategoryPO po);

	CategoryPO findCategoryById(Long id);
	/**
	 * 根据上级节点信息查询类目信息
	 * @param parentId
	 * @return
	 */
	CategoryPO categoryByPIdNode(Long parentId);
	/**
	 * 根据suId查询su所属类目
	 * @param suId
	 * @return
	 */
	List<String> findCategoryNameBySuId(Long suId);
}
	