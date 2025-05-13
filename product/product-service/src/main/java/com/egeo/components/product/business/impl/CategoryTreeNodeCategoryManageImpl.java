package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CategoryTreeNodeCategoryManage;
import com.egeo.components.product.facade.CategoryTreeNodeCategoryFacade;
import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("categoryTreeNodeCategory")
public class CategoryTreeNodeCategoryManageImpl implements CategoryTreeNodeCategoryManage{

	
	@Resource(name="categoryTreeNodeCategoryFacade")
	private CategoryTreeNodeCategoryFacade categoryTreeNodeCategoryFacade;

	@Override
	public CategoryTreeNodeCategoryDTO findCategoryTreeNodeCategoryById(CategoryTreeNodeCategoryDTO dto) {
		return categoryTreeNodeCategoryFacade.findCategoryTreeNodeCategoryById(dto);
	}

	@Override
	public PageResult<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryOfPage(CategoryTreeNodeCategoryDTO dto, Pagination page) {
		return categoryTreeNodeCategoryFacade.findCategoryTreeNodeCategoryOfPage(dto, page);
	}

	@Override
	public List<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryAll(CategoryTreeNodeCategoryDTO dto) {
		return categoryTreeNodeCategoryFacade.findCategoryTreeNodeCategoryAll(dto);
	}

	@Override
	public Long insertCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto) {
		return categoryTreeNodeCategoryFacade.insertCategoryTreeNodeCategoryWithTx(dto);
	}

	@Override
	public int updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto) {
		return categoryTreeNodeCategoryFacade.updateCategoryTreeNodeCategoryWithTx(dto);
	}

	@Override
	public int deleteCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto) {
		return categoryTreeNodeCategoryFacade.deleteCategoryTreeNodeCategoryWithTx(dto);
	}
	/**
	 * 批量添加前台类目节点与后台类目节点关系
	 * @param frontCategoryTreeNodeId
	 * @param queenCategoryTreeNodeIdList
	 * @return
	 */
	@Override
	public boolean insertCategoryTreeNodeCategoryAllWithTx(Long frontCategoryTreeNodeId,
			List<Long> queenCategoryTreeNodeIdList) {
		// TODO Auto-generated method stub
		return categoryTreeNodeCategoryFacade.insertCategoryTreeNodeCategoryAllWithTx(frontCategoryTreeNodeId,queenCategoryTreeNodeIdList);
	}
	/**
	 * 根据前台类目节点查询后台类目节点
	 * @param categoryTreeNodeId
	 * @return
	 */
	@Override
	public List<Long> findCategoryTreeNodeId(Long categoryTreeNodeId) {
		// TODO Auto-generated method stub
		return categoryTreeNodeCategoryFacade.findCategoryTreeNodeId(categoryTreeNodeId);
	}

	@Override
	public boolean insertCtnAndSucWithTx(Long frontCategoryTreeNodeId, List<Long> queenCategoryTreeNodeIds, List<Long> standardUnitCombinationIds) {
		return categoryTreeNodeCategoryFacade.insertCtnAndSucWithTx(frontCategoryTreeNodeId,
				queenCategoryTreeNodeIds, standardUnitCombinationIds);
	}


}
	