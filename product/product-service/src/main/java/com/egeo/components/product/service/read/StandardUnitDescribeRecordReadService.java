package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitDescribeRecordReadService {

	public StandardUnitDescribeRecordDTO findStandardUnitDescribeRecordById(StandardUnitDescribeRecordDTO dto);

	public PageResult<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordOfPage(StandardUnitDescribeRecordDTO dto,Pagination page);

	public List<StandardUnitDescribeRecordDTO> findStandardUnitDescribeRecordAll(StandardUnitDescribeRecordDTO dto);
}
	