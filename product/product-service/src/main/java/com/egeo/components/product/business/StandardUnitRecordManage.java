package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitRecordManage {

	public StandardUnitRecordDTO findStandardUnitRecordById(StandardUnitRecordDTO dto);	

	public PageResult<StandardUnitRecordDTO> findStandardUnitRecordOfPage(StandardUnitRecordDTO dto,Pagination page);

	public List<StandardUnitRecordDTO> findStandardUnitRecordAll(StandardUnitRecordDTO dto);

	Long insertStandardUnitRecordWithTx(StandardUnitRecordDTO dto);

	int updateStandardUnitRecordWithTx(StandardUnitRecordDTO dto);

	int deleteStandardUnitRecordWithTx(StandardUnitRecordDTO dto);
}
	