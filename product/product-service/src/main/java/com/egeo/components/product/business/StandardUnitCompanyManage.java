package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitCompanyManage {

	public StandardUnitCompanyDTO findStandardUnitCompanyById(StandardUnitCompanyDTO dto);	

	public PageResult<StandardUnitCompanyDTO> findStandardUnitCompanyOfPage(StandardUnitCompanyDTO dto,Pagination page);

	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(StandardUnitCompanyDTO dto);

	Long insertStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto);

	int updateStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto);

	int deleteStandardUnitCompanyWithTx(StandardUnitCompanyDTO dto);
}
	