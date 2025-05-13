package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitRecordReadService {

	public StandardUnitRecordDTO findStandardUnitRecordById(StandardUnitRecordDTO dto);

	public PageResult<StandardUnitRecordDTO> findStandardUnitRecordOfPage(StandardUnitRecordDTO dto,Pagination page);

	public List<StandardUnitRecordDTO> findStandardUnitRecordAll(StandardUnitRecordDTO dto);
}
	