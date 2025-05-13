package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitAttNameRecordReadService {

	public StandardProductUnitAttNameRecordDTO findStandardProductUnitAttNameRecordById(StandardProductUnitAttNameRecordDTO dto);

	public PageResult<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordOfPage(StandardProductUnitAttNameRecordDTO dto,Pagination page);

	public List<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordAll(StandardProductUnitAttNameRecordDTO dto);
}
	