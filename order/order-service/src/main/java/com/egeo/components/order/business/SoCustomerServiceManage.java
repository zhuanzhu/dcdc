package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoCustomerServiceManage {

	public SoCustomerServiceDTO findSoCustomerServiceById(SoCustomerServiceDTO dto);	

	public PageResult<SoCustomerServiceDTO> findSoCustomerServiceOfPage(SoCustomerServiceDTO dto,Pagination page);

	public List<SoCustomerServiceDTO> findSoCustomerServiceAll(SoCustomerServiceDTO dto);

	Long insertSoCustomerServiceWithTx(SoCustomerServiceDTO dto);

	int updateSoCustomerServiceWithTx(SoCustomerServiceDTO dto);

	int deleteSoCustomerServiceWithTx(SoCustomerServiceDTO dto);
}
	