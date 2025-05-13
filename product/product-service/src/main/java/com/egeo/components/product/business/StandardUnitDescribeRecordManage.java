package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitDescribeRecordManage {

	public StandardUnitDescribeRecordDTO findStandardUnitDescribeRecordById(StandardUnitDescribeRecordDTO dto);	

	public PageResult<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordOfPage(StandardUnitDescribeRecordDTO dto,Pagination page);

	public List<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordAll(StandardUnitDescribeRecordDTO dto);

	Long insertStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto);

	int updateStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto);

	int deleteStandardUnitDescribeRecordWithTx(StandardUnitDescribeRecordDTO dto);
}
	