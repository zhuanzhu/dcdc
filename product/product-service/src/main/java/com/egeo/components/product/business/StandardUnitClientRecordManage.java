package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitClientRecordManage {

	public StandardUnitClientRecordDTO findStandardUnitClientRecordById(StandardUnitClientRecordDTO dto);	

	public PageResult<StandardUnitClientRecordDTO> findStandardUnitClientRecordOfPage(StandardUnitClientRecordDTO dto,Pagination page);

	public List<StandardUnitClientRecordDTO> findStandardUnitClientRecordAll(StandardUnitClientRecordDTO dto);

	Long insertStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto);

	int updateStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto);

	int deleteStandardUnitClientRecordWithTx(StandardUnitClientRecordDTO dto);
}
	