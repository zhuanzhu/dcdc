package com.egeo.components.cms.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.CategoryTreeTemplateReadService;
import com.egeo.components.cms.service.write.CategoryTreeTemplateWriteService;
import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CategoryTreeTemplateFacade {
	
	@Resource
	private CategoryTreeTemplateReadService categoryTreeTemplateReadService;
	
	@Resource
	private CategoryTreeTemplateWriteService categoryTreeTemplateWriteService;
	
	
	public CategoryTreeTemplateDTO findCategoryTreeTemplateById(CategoryTreeTemplateDTO dto){
		
		return categoryTreeTemplateReadService.findCategoryTreeTemplateById(dto);
	}

	public PageResult<CategoryTreeTemplateDTO> findCategoryTreeTemplateOfPage(CategoryTreeTemplateDTO dto,Pagination page){
		
		return categoryTreeTemplateReadService.findCategoryTreeTemplateOfPage(dto, page);
		
	}

	public List<Map<String, Object>> findCategoryTreeTemplateAll(CategoryTreeTemplateDTO dto){
		List<Map<String, Object>> list = new ArrayList<>();
		List<CategoryTreeTemplateDTO> categoryTreeTemplateList = categoryTreeTemplateReadService.findCategoryTreeTemplateAll(dto);
		for (CategoryTreeTemplateDTO categoryTreeTemplateDTO : categoryTreeTemplateList) {
			Map<String, Object> map = new HashMap<>();
			map.put("categoryTreeTemplateId", categoryTreeTemplateDTO.getId());
			map.put("templateName", categoryTreeTemplateDTO.getTemplateName());
			list.add(map);
		}
		return list;
		
	}

	public Long insertCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto){
		
		return categoryTreeTemplateWriteService.insertCategoryTreeTemplateWithTx(dto);
	}

	public int updateCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto){
		
		return categoryTreeTemplateWriteService.updateCategoryTreeTemplateWithTx(dto);
	}

	public int deleteCategoryTreeTemplateWithTx(CategoryTreeTemplateDTO dto){
		
		return categoryTreeTemplateWriteService.deleteCategoryTreeTemplateWithTx(dto);
		
	}

}
	