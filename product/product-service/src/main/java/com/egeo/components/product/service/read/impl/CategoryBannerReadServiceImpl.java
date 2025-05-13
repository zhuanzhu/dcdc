package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CategoryBannerReadService;
import com.egeo.components.product.manage.read.CategoryBannerReadManage;
import com.egeo.components.product.converter.CategoryBannerConverter;
import com.egeo.components.product.dto.CategoryBannerDTO;
import com.egeo.components.product.po.CategoryBannerPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryBannerReadService")
public class CategoryBannerReadServiceImpl  implements CategoryBannerReadService {
	@Autowired
	private CategoryBannerReadManage categoryBannerReadManage;

	@Override
	public CategoryBannerDTO findCategoryBannerById(CategoryBannerDTO dto) {
		CategoryBannerPO po = CategoryBannerConverter.toPO(dto);
		CategoryBannerPO list = categoryBannerReadManage.findCategoryBannerById(po);		
		return CategoryBannerConverter.toDTO(list);
	}

	@Override
	public PageResult<CategoryBannerDTO> findCategoryBannerOfPage(CategoryBannerDTO dto, Pagination page) {
		CategoryBannerPO po = CategoryBannerConverter.toPO(dto);
        PageResult<CategoryBannerPO> pageResult = categoryBannerReadManage.findCategoryBannerOfPage(po, page);
        
        List<CategoryBannerDTO> list = CategoryBannerConverter.toDTO(pageResult.getList());
        PageResult<CategoryBannerDTO> result = new PageResult<CategoryBannerDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CategoryBannerDTO> findCategoryBannerAll(CategoryBannerDTO dto) {
		CategoryBannerPO po = CategoryBannerConverter.toPO(dto);
		List<CategoryBannerPO> list = categoryBannerReadManage.findCategoryBannerAll(po);		
		return CategoryBannerConverter.toDTO(list);
	}
}
	