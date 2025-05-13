package com.egeo.components.product.service.write;

import com.egeo.components.product.dto.CategoryTreeDTO;

public interface CategoryTreeWriteService {

	String addCategoryTreeWithTx(CategoryTreeDTO dto);

	boolean categoryTreeStartUsingWithTx(Long categoryTreeId,Integer companyType,Integer clientType, Long platformId);

	boolean categoryTreeStopUsingWithTx(Long categoryTreeId);

	/**
	 * 根据类目树id修改类目树信息
	 * @param vo
	 * @param req
	 * @return
	 */
	boolean updateCategoryTreeWithTx(CategoryTreeDTO dto);
}
	