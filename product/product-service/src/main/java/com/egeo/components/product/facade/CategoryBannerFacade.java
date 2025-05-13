package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CategoryBannerReadService;
import com.egeo.components.product.service.write.CategoryBannerWriteService;
import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CategoryBannerFacade {
	
	@Resource
	private CategoryBannerReadService categoryBannerReadService;
	
	@Resource
	private CategoryBannerWriteService categoryBannerWriteService;
	
	
	public CategoryBannerDTO findCategoryBannerById(CategoryBannerDTO dto){
		
		return categoryBannerReadService.findCategoryBannerById(dto);
	}

	public PageResult<CategoryBannerDTO> findCategoryBannerOfPage(CategoryBannerDTO dto,Pagination page){
		
		return categoryBannerReadService.findCategoryBannerOfPage(dto, page);
		
	}

	public List<CategoryBannerDTO> findCategoryBannerAll(CategoryBannerDTO dto){
		
		return categoryBannerReadService.findCategoryBannerAll(dto);
		
	}

	public Long insertCategoryBannerWithTx(CategoryBannerDTO dto){
		
		return categoryBannerWriteService.insertCategoryBannerWithTx(dto);
	}

	public int updateCategoryBannerWithTx(CategoryBannerDTO dto){
		
		return categoryBannerWriteService.updateCategoryBannerWithTx(dto);
	}

	public int deleteCategoryBannerWithTx(CategoryBannerDTO dto){
		
		return categoryBannerWriteService.deleteCategoryBannerWithTx(dto);
		
	}

}
	