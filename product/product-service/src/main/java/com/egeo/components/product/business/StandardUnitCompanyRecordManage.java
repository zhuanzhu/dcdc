package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCompanyRecordManage {

	public StandardUnitCompanyRecordDTO findStandardUnitCompanyRecordById(StandardUnitCompanyRecordDTO dto);	

	public PageResult<StandardUnitCompanyRecordDTO> findStandardUnitCompanyRecordOfPage(StandardUnitCompanyRecordDTO dto,Pagination page);

	public List<StandardUnitCompanyRecordDTO> findStandardUnitCompanyRecordAll(StandardUnitCompanyRecordDTO dto);

	Long insertStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto);

	int updateStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto);

	int deleteStandardUnitCompanyRecordWithTx(StandardUnitCompanyRecordDTO dto);
}
	