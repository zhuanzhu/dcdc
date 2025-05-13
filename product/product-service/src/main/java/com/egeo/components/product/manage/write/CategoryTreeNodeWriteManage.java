package com.egeo.components.product.manage.write;

import java.util.List;

import com.egeo.components.product.condition.CategoryTreeNodeCondition;
import com.egeo.components.product.po.CategoryTreeNodePO;

public interface CategoryTreeNodeWriteManage {

	Long addCategoryTreeNodeWithTx(CategoryTreeNodeCondition ctcc,List<Long> tagIdList);

	int updateCategoryTreeNodeWithTx(CategoryTreeNodePO po);

	int deleteByIdWithTx(CategoryTreeNodePO po);
	/**
	 * 添加前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @return
	 */
	Long saveCategoryTreeNodeWithTx(CategoryTreeNodeCondition po);
	/**
	 * 根据类目节点id修改前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	boolean updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCondition po);
	/**
	 * 根据前台类目节点id删除前台类目节点及子节点信息
	 * @param req
	 * @return
	 */
	boolean delByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId);
	
	Long insertCategoryTreeNodeWithTx(CategoryTreeNodePO ctnPo, String name, String catId);
}
	