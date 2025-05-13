package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CategoryTreeReadService;
import com.egeo.components.product.manage.read.CategoryTreeReadManage;
import com.egeo.components.product.converter.CategoryTreeConverter;
import com.egeo.components.product.dto.CategoryTreeDTO;
import com.egeo.components.product.po.CategoryTreePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("categoryTreeReadService")
public class CategoryTreeReadServiceImpl  implements CategoryTreeReadService {
	@Autowired
	private CategoryTreeReadManage categoryTreeReadManage;

	@Override
	public List<CategoryTreeDTO> queryAllCategoryTreeByParam(CategoryTreeDTO dto) {
		
		CategoryTreePO po = CategoryTreeConverter.toPO(dto);
		
		return CategoryTreeConverter.toDTO(categoryTreeReadManage.queryAllCategoryTreeByParam(po));
	}
	/**
	 * 根据条件查询类目树
	 * @param categoryTreeDTO
	 * @return
	 */
	@Override
	public List<CategoryTreeDTO> findCategoryAll(CategoryTreeDTO categoryTreeDTO) {
		List<CategoryTreePO> CategoryTreeList = categoryTreeReadManage.findCategoryAll(CategoryTreeConverter.toPO(categoryTreeDTO));
		return CategoryTreeConverter.toDTO(CategoryTreeList);
	}
	/**
	 * 分页查询前台类目树
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<CategoryTreeDTO> findCategoryTreeOfPage(CategoryTreeDTO dto, Pagination page) {
		PageResult<CategoryTreePO> pageResult = categoryTreeReadManage.findCategoryTreeOfPage(CategoryTreeConverter.toPO(dto), page);
		List<CategoryTreeDTO> list = CategoryTreeConverter.toDTO(pageResult.getList());
		PageResult<CategoryTreeDTO> result = new PageResult<>();
		result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
		return result;
	}
	/**
	 * 根据类目树id查询类目树信息
	 */
	@Override
	public CategoryTreeDTO findByCategoryTreeId(Long categoryTreeId) {
		CategoryTreePO categoryTreePO = categoryTreeReadManage.findByCategoryTreeId(categoryTreeId);
		if(EmptyUtil.isNotEmpty(categoryTreePO)){
			return CategoryTreeConverter.toDTO(categoryTreePO);
		}
		return null;
	}
	/**
	 * 查询所有类目树信息
	 */
	@Override
	public List<CategoryTreeDTO> findCategoryTreeAll(Long platformId) {
		List<CategoryTreePO> categoryTreePOs = categoryTreeReadManage.findCategoryTreeAll(platformId);
		return CategoryTreeConverter.toDTO(categoryTreePOs);
	}


}
	