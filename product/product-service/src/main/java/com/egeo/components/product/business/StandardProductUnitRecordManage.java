package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitRecordManage {

	public StandardProductUnitRecordDTO findStandardProductUnitRecordById(StandardProductUnitRecordDTO dto);	

	public PageResult<StandardProductUnitRecordDTO> findStandardProductUnitRecordOfPage(StandardProductUnitRecordDTO dto,Pagination page);

	public List<StandardProductUnitRecordDTO> findStandardProductUnitRecordAll(StandardProductUnitRecordDTO dto);

	Long insertStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto);

	int updateStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto);

	int deleteStandardProductUnitRecordWithTx(StandardProductUnitRecordDTO dto);
}
	