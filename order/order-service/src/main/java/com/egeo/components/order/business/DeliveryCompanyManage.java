package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DeliveryCompanyManage {

	public DeliveryCompanyDTO findDeliveryCompanyById(Long id);	

	public PageResult<DeliveryCompanyDTO> findDeliveryCompanyOfPage(DeliveryCompanyDTO dto,Pagination page);

	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(DeliveryCompanyDTO dto);

	int insertDeliveryCompanyWithTx(DeliveryCompanyDTO dto);

	int updateDeliveryCompanyWithTx(DeliveryCompanyDTO dto);

	int deleteDeliveryCompanyWithTx(DeliveryCompanyDTO dto);
}
	