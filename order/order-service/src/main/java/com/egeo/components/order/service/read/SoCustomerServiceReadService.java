package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoCustomerServiceReadService {

	public SoCustomerServiceDTO findSoCustomerServiceById(SoCustomerServiceDTO dto);

	public PageResult<SoCustomerServiceDTO> findSoCustomerServiceOfPage(SoCustomerServiceDTO dto,Pagination page);

	public List<SoCustomerServiceDTO> findSoCustomerServiceAll(SoCustomerServiceDTO dto);

	/**
	 * 根据子订单id查询售后信息
	 * @param scId
	 * @return
	 */
	public SoCustomerServiceDTO queryCustomerServiceBySoChildId(Long scId);
}
	