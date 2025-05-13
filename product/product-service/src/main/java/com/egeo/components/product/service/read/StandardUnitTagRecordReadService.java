package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitTagRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitTagRecordReadService {

	public StandardUnitTagRecordDTO findStandardUnitTagRecordById(StandardUnitTagRecordDTO dto);

	public PageResult<StandardUnitTagRecordDTO> findStandardUnitTagRecordOfPage(StandardUnitTagRecordDTO dto,Pagination page);

	public List<StandardUnitTagRecordDTO> findStandardUnitTagRecordAll(StandardUnitTagRecordDTO dto);
}
	