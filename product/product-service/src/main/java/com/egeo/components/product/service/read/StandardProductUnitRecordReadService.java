package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitRecordReadService {

	public StandardProductUnitRecordDTO findStandardProductUnitRecordById(StandardProductUnitRecordDTO dto);

	public PageResult<StandardProductUnitRecordDTO> findStandardProductUnitRecordOfPage(StandardProductUnitRecordDTO dto,Pagination page);

	public List<StandardProductUnitRecordDTO> findStandardProductUnitRecordAll(StandardProductUnitRecordDTO dto);
}
	