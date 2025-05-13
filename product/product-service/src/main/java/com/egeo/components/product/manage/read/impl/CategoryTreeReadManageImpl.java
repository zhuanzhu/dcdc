package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CategoryTreeReadManage;
import com.egeo.components.product.dao.read.CategoryTreeReadDAO;
import com.egeo.components.product.po.CategoryTreePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service
public class CategoryTreeReadManageImpl implements CategoryTreeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CategoryTreeReadDAO categoryTreeReadDAO;
	@Override
	public List<CategoryTreePO> queryAllCategoryTreeByParam(CategoryTreePO po) {
		
		return categoryTreeReadDAO.findAll(po,null);
	}
	/**
	 * 根据条件查询类目树
	 * @param categoryTreeDTO
	 * @return
	 */
	@Override
	public List<CategoryTreePO> findCategoryAll(CategoryTreePO po) {
		// TODO Auto-generated method stub
		return categoryTreeReadDAO.findAll(po,null);
	}
	/**
	 * 分页查询前台类目树
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<CategoryTreePO> findCategoryTreeOfPage(CategoryTreePO po, Pagination page) {
		PageResult<CategoryTreePO> pageResult = new PageResult<CategoryTreePO>();
		List<CategoryTreePO> list = null;

		int cnt = categoryTreeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = categoryTreeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CategoryTreePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	/**
	 * 根据类目树id查询类目树信息
	 */
	@Override
	public CategoryTreePO findByCategoryTreeId(Long categoryTreeId) {
		CategoryTreePO categoryTreePO = new CategoryTreePO();
		categoryTreePO.setId(categoryTreeId);
		return categoryTreeReadDAO.findById(categoryTreePO);
	}
	/**
	 * 查询所有类目树信息
	 */
	@Override
	public List<CategoryTreePO> findCategoryTreeAll(Long platformId) {
		CategoryTreePO categoryTreePO = new CategoryTreePO();
		categoryTreePO.setPlatformId(platformId);
		return categoryTreeReadDAO.findAll(categoryTreePO,null);
	}

	
}
	