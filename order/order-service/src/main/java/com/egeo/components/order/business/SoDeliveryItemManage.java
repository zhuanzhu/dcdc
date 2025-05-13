package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoDeliveryItemManage {

	public SoDeliveryItemDTO findSoDeliveryItemById(SoDeliveryItemDTO dto);	

	public PageResult<SoDeliveryItemDTO> findSoDeliveryItemOfPage(SoDeliveryItemDTO dto,Pagination page);

	public List<SoDeliveryItemDTO> findSoDeliveryItemAll(SoDeliveryItemDTO dto);

	int insertSoDeliveryItemWithTx(SoDeliveryItemDTO dto);

	int updateSoDeliveryItemWithTx(SoDeliveryItemDTO dto);

	int deleteSoDeliveryItemWithTx(SoDeliveryItemDTO dto);
}
	