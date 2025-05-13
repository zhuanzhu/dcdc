package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitAttValueRecordReadService {

	public StandardProductUnitAttValueRecordDTO findStandardProductUnitAttValueRecordById(StandardProductUnitAttValueRecordDTO dto);

	public PageResult<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordOfPage(StandardProductUnitAttValueRecordDTO dto,Pagination page);

	public List<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordAll(StandardProductUnitAttValueRecordDTO dto);
}
	