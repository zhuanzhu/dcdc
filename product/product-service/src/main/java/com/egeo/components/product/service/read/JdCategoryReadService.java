package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.JdCategoryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface JdCategoryReadService {

	public JdCategoryDTO findJdCategoryById(JdCategoryDTO dto);

	public PageResult<JdCategoryDTO> findJdCategoryOfPage(JdCategoryDTO dto,Pagination page);

	public List<JdCategoryDTO> findJdCategoryAll(JdCategoryDTO dto);

    List<Long> findJdCategoryIdByCatClass(int catClass);

}
	