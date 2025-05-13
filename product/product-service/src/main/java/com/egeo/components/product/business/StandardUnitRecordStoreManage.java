package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitRecordStoreDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitRecordStoreManage {

	public StandardUnitRecordStoreDTO findStandardUnitRecordStoreById(StandardUnitRecordStoreDTO dto);	

	public PageResult<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreOfPage(StandardUnitRecordStoreDTO dto,Pagination page);

	public List<StandardUnitRecordStoreDTO> findStandardUnitRecordStoreAll(StandardUnitRecordStoreDTO dto);

	Long insertStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto);

	int updateStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto);

	int deleteStandardUnitRecordStoreWithTx(StandardUnitRecordStoreDTO dto);
}
	