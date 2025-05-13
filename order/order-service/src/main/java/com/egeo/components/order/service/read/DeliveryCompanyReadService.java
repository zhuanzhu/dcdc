package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface DeliveryCompanyReadService {

	public DeliveryCompanyDTO findDeliveryCompanyById(Long id);

	public PageResult<DeliveryCompanyDTO> findDeliveryCompanyOfPage(DeliveryCompanyDTO dto,Pagination page);

	public List<DeliveryCompanyDTO> findDeliveryCompanyAll(DeliveryCompanyDTO dto);

	/**
	 * 根据名称查询物流公司
	 * @param name
	 * @return
	 */
	public DeliveryCompanyDTO queryDeliveryCompanyByName(String name);
}
	