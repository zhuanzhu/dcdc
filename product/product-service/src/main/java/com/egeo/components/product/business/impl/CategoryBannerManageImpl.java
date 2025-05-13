package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CategoryBannerManage;
import com.egeo.components.product.facade.CategoryBannerFacade;
import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryBanner")
public class CategoryBannerManageImpl implements CategoryBannerManage{

	
	@Resource(name="categoryBannerFacade")
	private CategoryBannerFacade categoryBannerFacade;

	@Override
	public CategoryBannerDTO findCategoryBannerById(CategoryBannerDTO dto) {
		return categoryBannerFacade.findCategoryBannerById(dto);
	}

	@Override
	public PageResult<CategoryBannerDTO> findCategoryBannerOfPage(CategoryBannerDTO dto, Pagination page) {
		return categoryBannerFacade.findCategoryBannerOfPage(dto, page);
	}

	@Override
	public List<CategoryBannerDTO> findCategoryBannerAll(CategoryBannerDTO dto) {
		return categoryBannerFacade.findCategoryBannerAll(dto);
	}

	@Override
	public Long insertCategoryBannerWithTx(CategoryBannerDTO dto) {
		return categoryBannerFacade.insertCategoryBannerWithTx(dto);
	}

	@Override
	public int updateCategoryBannerWithTx(CategoryBannerDTO dto) {
		return categoryBannerFacade.updateCategoryBannerWithTx(dto);
	}

	@Override
	public int deleteCategoryBannerWithTx(CategoryBannerDTO dto) {
		return categoryBannerFacade.deleteCategoryBannerWithTx(dto);
	}


}
	