package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.DeliveryFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DeliveryFlowManage {

	public DeliveryFlowDTO findDeliveryFlowById(DeliveryFlowDTO dto);	

	public PageResult<DeliveryFlowDTO> findDeliveryFlowOfPage(DeliveryFlowDTO dto,Pagination page);

	public List<DeliveryFlowDTO> findDeliveryFlowAll(DeliveryFlowDTO dto);

	Long insertDeliveryFlowWithTx(DeliveryFlowDTO dto);

	int updateDeliveryFlowWithTx(DeliveryFlowDTO dto);

	int deleteDeliveryFlowWithTx(DeliveryFlowDTO dto);
}
	