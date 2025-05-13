package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitAttValueRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitAttValueRecordManage {

	public StandardProductUnitAttValueRecordDTO findStandardProductUnitAttValueRecordById(StandardProductUnitAttValueRecordDTO dto);	

	public PageResult<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordOfPage(StandardProductUnitAttValueRecordDTO dto,Pagination page);

	public List<StandardProductUnitAttValueRecordDTO> findStandardProductUnitAttValueRecordAll(StandardProductUnitAttValueRecordDTO dto);

	Long insertStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto);

	int updateStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto);

	int deleteStandardProductUnitAttValueRecordWithTx(StandardProductUnitAttValueRecordDTO dto);
}
	