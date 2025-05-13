package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitTagReadService {

	public StandardUnitTagDTO findStandardUnitTagById(StandardUnitTagDTO dto);

	public PageResult<StandardUnitTagDTO> findStandardUnitTagOfPage(StandardUnitTagDTO dto,Pagination page);

	public List<StandardUnitTagDTO> findStandardUnitTagAll(StandardUnitTagDTO dto);
}
	