package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitDescriptionRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardProductUnitDescriptionRecordReadService {

	public StandardProductUnitDescriptionRecordDTO findStandardProductUnitDescriptionRecordById(StandardProductUnitDescriptionRecordDTO dto);

	public PageResult<StandardProductUnitDescriptionRecordDTO> findStandardProductUnitDescriptionRecordOfPage(StandardProductUnitDescriptionRecordDTO dto,Pagination page);

	public List<StandardProductUnitDescriptionRecordDTO> findStandardProductUnitDescriptionRecordAll(StandardProductUnitDescriptionRecordDTO dto);
}
	