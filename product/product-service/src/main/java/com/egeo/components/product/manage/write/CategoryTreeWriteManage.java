package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.CategoryTreePO;

public interface CategoryTreeWriteManage {

	String addCategoryTreeWithTx(CategoryTreePO po);
	/**
	 * 根据类目树id将类目树设为启用
	 */
	boolean categoryTreeStartUsingWithTx(Long categoryTreeId,Integer companyType,Integer clientType, Long platformId);

	boolean categoryTreeStopUsingWithTx(Long categoryTreeId);
	/**
	 * 根据类目树id修改类目树信息
	 * @param vo
	 * @param req
	 * @return
	 */
	boolean updateCategoryTreeWithTx(CategoryTreePO po);
}
	