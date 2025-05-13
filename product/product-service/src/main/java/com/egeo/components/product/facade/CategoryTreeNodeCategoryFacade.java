package com.egeo.components.product.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CategoryTreeNodeCategoryReadService;
import com.egeo.components.product.service.write.CategoryTreeNodeCategoryWriteService;
import com.egeo.components.product.dto.CategoryTreeNodeCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CategoryTreeNodeCategoryFacade {
	
	@Resource
	private CategoryTreeNodeCategoryReadService categoryTreeNodeCategoryReadService;
	
	@Resource
	private CategoryTreeNodeCategoryWriteService categoryTreeNodeCategoryWriteService;
	
	
	public CategoryTreeNodeCategoryDTO findCategoryTreeNodeCategoryById(CategoryTreeNodeCategoryDTO dto){
		
		return categoryTreeNodeCategoryReadService.findCategoryTreeNodeCategoryById(dto);
	}

	public PageResult<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryOfPage(CategoryTreeNodeCategoryDTO dto,Pagination page){
		
		return categoryTreeNodeCategoryReadService.findCategoryTreeNodeCategoryOfPage(dto, page);
		
	}

	public List<CategoryTreeNodeCategoryDTO> findCategoryTreeNodeCategoryAll(CategoryTreeNodeCategoryDTO dto){
		
		return categoryTreeNodeCategoryReadService.findCategoryTreeNodeCategoryAll(dto);
		
	}

	public Long insertCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto){
		
		return categoryTreeNodeCategoryWriteService.insertCategoryTreeNodeCategoryWithTx(dto);
	}

	public int updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto){
		
		return categoryTreeNodeCategoryWriteService.updateCategoryTreeNodeCategoryWithTx(dto);
	}

	public int deleteCategoryTreeNodeCategoryWithTx(CategoryTreeNodeCategoryDTO dto){
		
		return categoryTreeNodeCategoryWriteService.deleteCategoryTreeNodeCategoryWithTx(dto);
		
	}
	/**
	 * 批量添加前台类目节点与后台类目节点关系
	 * @param frontCategoryTreeNodeId
	 * @param queenCategoryTreeNodeIdList
	 * @return
	 */
	public boolean insertCategoryTreeNodeCategoryAllWithTx(Long frontCategoryTreeNodeId,
			List<Long> queenCategoryTreeNodeIdList) {
		// TODO Auto-generated method stub
		return categoryTreeNodeCategoryWriteService.insertCategoryTreeNodeCategoryAllWithTx(frontCategoryTreeNodeId,queenCategoryTreeNodeIdList);
	}
	/**
	 * 根据前台类目节点查询后台类目节点
	 * @param categoryTreeNodeId
	 * @return
	 */
	public List<Long> findCategoryTreeNodeId(Long categoryTreeNodeId) {
		List<Long> list = new ArrayList<>();
		CategoryTreeNodeCategoryDTO categoryTreeNodeCategoryDTO = new CategoryTreeNodeCategoryDTO();
		categoryTreeNodeCategoryDTO.setFrontCategoryTreeNodeId(categoryTreeNodeId);
		List<CategoryTreeNodeCategoryDTO> categoryTreeNodeCategoryList = categoryTreeNodeCategoryReadService.findCategoryTreeNodeCategoryAll(categoryTreeNodeCategoryDTO);
		for (CategoryTreeNodeCategoryDTO categoryTreeNodeCategoryDTO2 : categoryTreeNodeCategoryList) {
			list.add(categoryTreeNodeCategoryDTO2.getQueenCategoryTreeNodeId());
		}
		return list;
	}

	/**
	 * 前台类目节点关联后台类目节点与商品组合
	 * @param frontCategoryTreeNodeId
	 * @param queenCategoryTreeNodeIds
	 * @param standardUnitCombinationIds
	 * @return
	 */
	public boolean insertCtnAndSucWithTx(Long frontCategoryTreeNodeId, List<Long> queenCategoryTreeNodeIds, List<Long> standardUnitCombinationIds) {
		return categoryTreeNodeCategoryWriteService.insertCtnAndSucWithTx(frontCategoryTreeNodeId,
				queenCategoryTreeNodeIds, standardUnitCombinationIds);
	}
}
	