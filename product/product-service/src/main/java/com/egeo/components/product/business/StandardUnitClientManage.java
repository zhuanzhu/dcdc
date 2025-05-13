package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitClientManage {

	public StandardUnitClientDTO findStandardUnitClientById(StandardUnitClientDTO dto);	

	public PageResult<StandardUnitClientDTO> findStandardUnitClientOfPage(StandardUnitClientDTO dto,Pagination page);

	public List<StandardUnitClientDTO> findStandardUnitClientAll(StandardUnitClientDTO dto);

	Long insertStandardUnitClientWithTx(StandardUnitClientDTO dto);

	int updateStandardUnitClientWithTx(StandardUnitClientDTO dto);

	int deleteStandardUnitClientWithTx(StandardUnitClientDTO dto);
}
	