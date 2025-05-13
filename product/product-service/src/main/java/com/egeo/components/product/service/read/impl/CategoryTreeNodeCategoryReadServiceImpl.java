package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CategoryTreeNodeCategoryReadService;
import com.egeo.components.product.manage.read.CategoryTreeNodeCategoryReadManage;
import com.egeo.components.product.converter.CategoryTreeNodeCategoryConverter;
import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;
import com.egeo.components.product.po.CategoryTreeNodeCategoryPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryTreeNodeCategoryReadService")
public class CategoryTreeNodeCategoryReadServiceImpl  implements CategoryTreeNodeCategoryReadService {
	@Autowired
	private CategoryTreeNodeCategoryReadManage categoryTreeNodeCategoryReadManage;

	@Override
	public CategoryTreeNodeCategoryDTO findCategoryTreeNodeCategoryById(CategoryTreeNodeCategoryDTO dto) {
		CategoryTreeNodeCategoryPO po = CategoryTreeNodeCategoryConverter.toPO(dto);
		CategoryTreeNodeCategoryPO list = categoryTreeNodeCategoryReadManage.findCategoryTreeNodeCategoryById(po);		
		return CategoryTreeNodeCategoryConverter.toDTO(list);
	}

	@Override
	public PageResult<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryOfPage(CategoryTreeNodeCategoryDTO dto, Pagination page) {
		CategoryTreeNodeCategoryPO po = CategoryTreeNodeCategoryConverter.toPO(dto);
        PageResult<CategoryTreeNodeCategoryPO> pageResult = categoryTreeNodeCategoryReadManage.findCategoryTreeNodeCategoryOfPage(po, page);
        
        List<CategoryTreeNodeCategoryDTO> list = CategoryTreeNodeCategoryConverter.toDTO(pageResult.getList());
        PageResult<CategoryTreeNodeCategoryDTO> result = new PageResult<CategoryTreeNodeCategoryDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryAll(CategoryTreeNodeCategoryDTO dto) {
		CategoryTreeNodeCategoryPO po = CategoryTreeNodeCategoryConverter.toPO(dto);
		List<CategoryTreeNodeCategoryPO> list = categoryTreeNodeCategoryReadManage.findCategoryTreeNodeCategoryAll(po);		
		return CategoryTreeNodeCategoryConverter.toDTO(list);
	}

	@Override
	public List<Long> findCategoryIdsByCategoryTreeNodeId(List<Long> categoryTreeNodeId) {
		return categoryTreeNodeCategoryReadManage.findCategoryIdsByCategoryTreeNodeId(categoryTreeNodeId);
	}
}
	