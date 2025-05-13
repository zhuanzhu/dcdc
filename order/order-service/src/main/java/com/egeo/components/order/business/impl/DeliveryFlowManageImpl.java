package com.egeo.components.order.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.order.business.DeliveryFlowManage;
import com.egeo.components.order.facade.DeliveryFlowFacade;
import com.egeo.components.order.dto.DeliveryFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("deliveryFlow")
public class DeliveryFlowManageImpl implements DeliveryFlowManage{

	
	@Resource(name="deliveryFlowFacade")
	private DeliveryFlowFacade deliveryFlowFacade;

	@Override
	public DeliveryFlowDTO findDeliveryFlowById(DeliveryFlowDTO dto) {
		return deliveryFlowFacade.findDeliveryFlowById(dto);
	}

	@Override
	public PageResult<DeliveryFlowDTO> findDeliveryFlowOfPage(DeliveryFlowDTO dto, Pagination page) {
		return deliveryFlowFacade.findDeliveryFlowOfPage(dto, page);
	}

	@Override
	public List<DeliveryFlowDTO> findDeliveryFlowAll(DeliveryFlowDTO dto) {
		return deliveryFlowFacade.findDeliveryFlowAll(dto);
	}

	@Override
	public Long insertDeliveryFlowWithTx(DeliveryFlowDTO dto) {
		return deliveryFlowFacade.insertDeliveryFlowWithTx(dto);
	}

	@Override
	public int updateDeliveryFlowWithTx(DeliveryFlowDTO dto) {
		return deliveryFlowFacade.updateDeliveryFlowWithTx(dto);
	}

	@Override
	public int deleteDeliveryFlowWithTx(DeliveryFlowDTO dto) {
		return deliveryFlowFacade.deleteDeliveryFlowWithTx(dto);
	}


}
	