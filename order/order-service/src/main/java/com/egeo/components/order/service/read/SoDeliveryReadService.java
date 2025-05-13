package com.egeo.components.order.service.read;


import java.util.List;
	
import com.egeo.components.order.dto.SoDeliveryDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SoDeliveryReadService {

	public SoDeliveryDTO findSoDeliveryById(SoDeliveryDTO dto);

	public PageResult<SoDeliveryDTO> findSoDeliveryOfPage(SoDeliveryDTO dto,Pagination page);

	public List<SoDeliveryDTO> findSoDeliveryAll(SoDeliveryDTO dto);
}
	