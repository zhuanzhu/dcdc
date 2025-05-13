package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardProductUnitAttNameRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardProductUnitAttNameRecordManage {

	public StandardProductUnitAttNameRecordDTO findStandardProductUnitAttNameRecordById(StandardProductUnitAttNameRecordDTO dto);	

	public PageResult<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordOfPage(StandardProductUnitAttNameRecordDTO dto,Pagination page);

	public List<StandardProductUnitAttNameRecordDTO> findStandardProductUnitAttNameRecordAll(StandardProductUnitAttNameRecordDTO dto);

	Long insertStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto);

	int updateStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto);

	int deleteStandardProductUnitAttNameRecordWithTx(StandardProductUnitAttNameRecordDTO dto);
}
	