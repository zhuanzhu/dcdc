package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryTreeWriteService;
import com.egeo.components.product.manage.write.CategoryTreeWriteManage;
import com.egeo.components.product.converter.CategoryTreeConverter;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.po.CategoryTreePO;

@Service("categoryTreeWriteService")
public class CategoryTreeWriteServiceImpl  implements CategoryTreeWriteService {
	@Autowired
	private CategoryTreeWriteManage categoryTreeWriteManage;

	@Override
	public String addCategoryTreeWithTx(CategoryTreeDTO dto) {
		CategoryTreePO po = CategoryTreeConverter.toPO(dto);
		return categoryTreeWriteManage.addCategoryTreeWithTx(po);
	}
	/**
	 * 根据类目树id将类目树设为启用
	 */
	@Override
	public boolean categoryTreeStartUsingWithTx(Long categoryTreeId,Integer companyType,Integer clientType, Long platformId) {
		// TODO Auto-generated method stub
		return categoryTreeWriteManage.categoryTreeStartUsingWithTx(categoryTreeId,companyType,clientType, platformId);
	}

	@Override
	public boolean categoryTreeStopUsingWithTx(Long categoryTreeId) {
		return categoryTreeWriteManage.categoryTreeStopUsingWithTx(categoryTreeId);
	}

	/**
	 * 根据类目树id修改类目树信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public boolean updateCategoryTreeWithTx(CategoryTreeDTO dto) {
		// TODO Auto-generated method stub
		return categoryTreeWriteManage.updateCategoryTreeWithTx(CategoryTreeConverter.toPO(dto));
	}
}
	