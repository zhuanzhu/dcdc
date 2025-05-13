package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CategoryTagReadService;
import com.egeo.components.product.manage.read.CategoryTagReadManage;
import com.egeo.components.product.converter.CategoryTagConverter;
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.components.product.po.CategoryTagPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryTagReadService")
public class CategoryTagReadServiceImpl  implements CategoryTagReadService {
	@Autowired
	private CategoryTagReadManage categoryTagReadManage;

	@Override
	public CategoryTagDTO findCategoryTagById(CategoryTagDTO dto) {
		CategoryTagPO po = CategoryTagConverter.toPO(dto);
		CategoryTagPO list = categoryTagReadManage.findCategoryTagById(po);		
		return CategoryTagConverter.toDTO(list);
	}

	@Override
	public PageResult<CategoryTagDTO> findCategoryTagOfPage(CategoryTagDTO dto, Pagination page) {
		CategoryTagPO po = CategoryTagConverter.toPO(dto);
        PageResult<CategoryTagPO> pageResult = categoryTagReadManage.findCategoryTagOfPage(po, page);
        
        List<CategoryTagDTO> list = CategoryTagConverter.toDTO(pageResult.getList());
        PageResult<CategoryTagDTO> result = new PageResult<CategoryTagDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CategoryTagDTO> findCategoryTagAll(CategoryTagDTO dto) {
		CategoryTagPO po = CategoryTagConverter.toPO(dto);
		List<CategoryTagPO> list = categoryTagReadManage.findCategoryTagAll(po);		
		return CategoryTagConverter.toDTO(list);
	}
}
	