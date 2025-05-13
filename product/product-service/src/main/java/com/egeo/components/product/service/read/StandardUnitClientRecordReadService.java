package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitClientRecordReadService {

	public StandardUnitClientRecordDTO findStandardUnitClientRecordById(StandardUnitClientRecordDTO dto);

	public PageResult<StandardUnitClientRecordDTO> findStandardUnitClientRecordOfPage(StandardUnitClientRecordDTO dto,Pagination page);

	public List<StandardUnitClientRecordDTO> findStandardUnitClientRecordAll(StandardUnitClientRecordDTO dto);
}
	