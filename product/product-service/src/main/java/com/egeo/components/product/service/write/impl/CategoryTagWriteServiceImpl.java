package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CategoryTagWriteService;
import com.egeo.components.product.manage.write.CategoryTagWriteManage;
import com.egeo.components.product.converter.CategoryTagConverter;
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.components.product.po.CategoryTagPO;

@Service("categoryTagWriteService")
public class CategoryTagWriteServiceImpl  implements CategoryTagWriteService {
	@Autowired
	private CategoryTagWriteManage categoryTagWriteManage;

	@Override
	public Long insertCategoryTagWithTx(CategoryTagDTO dto) {
		CategoryTagPO po = CategoryTagConverter.toPO(dto);
		Long rt = categoryTagWriteManage.insertCategoryTagWithTx(po);		
		return rt;
	}

	@Override
	public int updateCategoryTagWithTx(CategoryTagDTO dto) {
		CategoryTagPO po = CategoryTagConverter.toPO(dto);
		int rt = categoryTagWriteManage.updateCategoryTagWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCategoryTagWithTx(CategoryTagDTO dto) {
		CategoryTagPO po = CategoryTagConverter.toPO(dto);
		int rt = categoryTagWriteManage.deleteCategoryTagWithTx(po);		
		return rt;
	}
}
	