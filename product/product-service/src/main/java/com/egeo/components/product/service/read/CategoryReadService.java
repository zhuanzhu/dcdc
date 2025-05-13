package com.egeo.components.product.service.read;

import java.util.List;

import com.egeo.components.product.dto.CategoryDTO;

public interface CategoryReadService {

    List<CategoryDTO> findAll(CategoryDTO dto);

    List<String> idToName(String ids);

    List<CategoryDTO> findAllList(CategoryDTO dto);

	CategoryDTO findCategoryById(Long id);
	/**
	 * 根据上级节点信息查询类目信息
	 * @param parentId
	 * @return
	 */
	CategoryDTO categoryByPIdNode(Long parentId);



}
	