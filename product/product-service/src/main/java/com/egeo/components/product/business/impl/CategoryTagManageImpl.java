package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CategoryTagManage;
import com.egeo.components.product.facade.CategoryTagFacade;
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryTag")
public class CategoryTagManageImpl implements CategoryTagManage{

	
	@Resource(name="categoryTagFacade")
	private CategoryTagFacade categoryTagFacade;

	@Override
	public CategoryTagDTO findCategoryTagById(CategoryTagDTO dto) {
		return categoryTagFacade.findCategoryTagById(dto);
	}

	@Override
	public PageResult<CategoryTagDTO> findCategoryTagOfPage(CategoryTagDTO dto, Pagination page) {
		return categoryTagFacade.findCategoryTagOfPage(dto, page);
	}

	@Override
	public List<CategoryTagDTO> findCategoryTagAll(CategoryTagDTO dto) {
		return categoryTagFacade.findCategoryTagAll(dto);
	}

	@Override
	public Long insertCategoryTagWithTx(CategoryTagDTO dto) {
		return categoryTagFacade.insertCategoryTagWithTx(dto);
	}

	@Override
	public int updateCategoryTagWithTx(CategoryTagDTO dto) {
		return categoryTagFacade.updateCategoryTagWithTx(dto);
	}

	@Override
	public int deleteCategoryTagWithTx(CategoryTagDTO dto) {
		return categoryTagFacade.deleteCategoryTagWithTx(dto);
	}


}
	