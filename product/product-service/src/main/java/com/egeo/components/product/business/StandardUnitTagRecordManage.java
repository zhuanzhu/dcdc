package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitTagRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitTagRecordManage {

	public StandardUnitTagRecordDTO findStandardUnitTagRecordById(StandardUnitTagRecordDTO dto);	

	public PageResult<StandardUnitTagRecordDTO> findStandardUnitTagRecordOfPage(StandardUnitTagRecordDTO dto,Pagination page);

	public List<StandardUnitTagRecordDTO> findStandardUnitTagRecordAll(StandardUnitTagRecordDTO dto);

	Long insertStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto);

	int updateStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto);

	int deleteStandardUnitTagRecordWithTx(StandardUnitTagRecordDTO dto);
}
	