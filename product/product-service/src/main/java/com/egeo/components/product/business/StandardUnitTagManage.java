package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitTagManage {

	public StandardUnitTagDTO findStandardUnitTagById(StandardUnitTagDTO dto);	

	public PageResult<StandardUnitTagDTO> findStandardUnitTagOfPage(StandardUnitTagDTO dto,Pagination page);

	public List<StandardUnitTagDTO> findStandardUnitTagAll(StandardUnitTagDTO dto);

	Long insertStandardUnitTagWithTx(StandardUnitTagDTO dto);

	int updateStandardUnitTagWithTx(StandardUnitTagDTO dto);

	int deleteStandardUnitTagWithTx(StandardUnitTagDTO dto);
}
	