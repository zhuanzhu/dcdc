package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitRecordStoreReadService {

	public StandardUnitRecordStoreDTO findStandardUnitRecordStoreById(StandardUnitRecordStoreDTO dto);

	public PageResult<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreOfPage(StandardUnitRecordStoreDTO dto,Pagination page);

	public List<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreAll(StandardUnitRecordStoreDTO dto);
}
	