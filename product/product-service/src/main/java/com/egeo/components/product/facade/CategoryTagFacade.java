package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CategoryTagReadService;
import com.egeo.components.product.service.write.CategoryTagWriteService;
import com.egeo.components.product.dto.CategoryTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CategoryTagFacade {
	
	@Resource
	private CategoryTagReadService categoryTagReadService;
	
	@Resource
	private CategoryTagWriteService categoryTagWriteService;
	
	
	public CategoryTagDTO findCategoryTagById(CategoryTagDTO dto){
		
		return categoryTagReadService.findCategoryTagById(dto);
	}

	public PageResult<CategoryTagDTO> findCategoryTagOfPage(CategoryTagDTO dto,Pagination page){
		
		return categoryTagReadService.findCategoryTagOfPage(dto, page);
		
	}

	public List<CategoryTagDTO> findCategoryTagAll(CategoryTagDTO dto){
		
		return categoryTagReadService.findCategoryTagAll(dto);
		
	}

	public Long insertCategoryTagWithTx(CategoryTagDTO dto){
		
		return categoryTagWriteService.insertCategoryTagWithTx(dto);
	}

	public int updateCategoryTagWithTx(CategoryTagDTO dto){
		
		return categoryTagWriteService.updateCategoryTagWithTx(dto);
	}

	public int deleteCategoryTagWithTx(CategoryTagDTO dto){
		
		return categoryTagWriteService.deleteCategoryTagWithTx(dto);
		
	}

}
	