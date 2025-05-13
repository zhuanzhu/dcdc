package com.egeo.components.product.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.egeo.components.product.vo.CategoryVO;
import com.egeo.components.product.dto.CategoryDTO;

public interface CategoryManage {

    List<Map<String, Object>> findAll(CategoryVO categoryVO);

    List<String> idToName(String ids);

    List<CategoryDTO> findAllList(CategoryVO categoryVO);

	String modifyCategory(CategoryVO categoryVO,String listSort,List<Long> tagIdList,HttpServletRequest req);

	String deleteCategory(CategoryVO categoryVO, HttpServletRequest req);

	Map<String, Object> findCategorybyId(Long id);

	List<Long> idsByCategoryId(Long id);
	

}
	