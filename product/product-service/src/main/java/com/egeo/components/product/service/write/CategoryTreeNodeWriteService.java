package com.egeo.components.product.service.write;

import java.util.List;

import com.egeo.components.product.dto.CategoryTreeNodeDTO;


public interface CategoryTreeNodeWriteService {

	String addCategoryTreeNodeWithTx(CategoryTreeNodeDTO dto,List<Long> tagIdList);

	int updateCategoryTreeNodeWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO);
	/**
	 * 根据id删除类目节点信息
	 * @param categoryTreeNodeDTO
	 * @return
	 */
	int deleteByIdWithTx(CategoryTreeNodeDTO categoryTreeNodeDTO);
	/**
	 * 添加前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @return
	 */
	Long saveCategoryTreeNodeWithTx(CategoryTreeNodeDTO dto);
	/**
	 * 根据类目节点id修改前台类目节点信息
	 * @param categoryTreeNodeVO
	 * @param req
	 * @return
	 */
	boolean updateCategoryTreeNodeCategoryWithTx(CategoryTreeNodeDTO dto);
	/**
	 * 根据前台类目节点id删除前台类目节点及子节点信息
	 * @param req
	 * @return
	 */
	boolean delByCategoryTreeNodeIdWithTx(Long categoryTreeNodeId);
	
	Long insertCategoryTreeNodeWithTx(CategoryTreeNodeDTO ctnDTO, String name, String catId);
}
	