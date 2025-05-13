package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.CategoryTreeTemplateReadService;
import com.egeo.components.cms.manage.read.CategoryTreeTemplateReadManage;
import com.egeo.components.cms.converter.CategoryTreeTemplateConverter;
import com.egeo.components.cms.dto.CategoryTreeTemplateDTO;
import com.egeo.components.cms.po.CategoryTreeTemplatePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryTreeTemplateReadService")
public class CategoryTreeTemplateReadServiceImpl  implements CategoryTreeTemplateReadService {
	@Autowired
	private CategoryTreeTemplateReadManage categoryTreeTemplateReadManage;

	@Override
	public CategoryTreeTemplateDTO findCategoryTreeTemplateById(CategoryTreeTemplateDTO dto) {
		CategoryTreeTemplatePO po = CategoryTreeTemplateConverter.toPO(dto);
		CategoryTreeTemplatePO list = categoryTreeTemplateReadManage.findCategoryTreeTemplateById(po);		
		return CategoryTreeTemplateConverter.toDTO(list);
	}

	@Override
	public PageResult<CategoryTreeTemplateDTO> findCategoryTreeTemplateOfPage(CategoryTreeTemplateDTO dto, Pagination page) {
		CategoryTreeTemplatePO po = CategoryTreeTemplateConverter.toPO(dto);
        PageResult<CategoryTreeTemplatePO> pageResult = categoryTreeTemplateReadManage.findCategoryTreeTemplateOfPage(po, page);
        
        List<CategoryTreeTemplateDTO> list = CategoryTreeTemplateConverter.toDTO(pageResult.getList());
        PageResult<CategoryTreeTemplateDTO> result = new PageResult<CategoryTreeTemplateDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CategoryTreeTemplateDTO> findCategoryTreeTemplateAll(CategoryTreeTemplateDTO dto) {
		CategoryTreeTemplatePO po = CategoryTreeTemplateConverter.toPO(dto);
		List<CategoryTreeTemplatePO> list = categoryTreeTemplateReadManage.findCategoryTreeTemplateAll(po);		
		return CategoryTreeTemplateConverter.toDTO(list);
	}
}
	