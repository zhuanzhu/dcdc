package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.DeliveryFlowWriteService;
import com.egeo.components.order.manage.write.DeliveryFlowWriteManage;
import com.egeo.components.order.converter.DeliveryFlowConverter;
import com.egeo.components.order.dto.DeliveryFlowDTO;
import com.egeo.components.order.po.DeliveryFlowPO;

@Service("deliveryFlowWriteService")
public class DeliveryFlowWriteServiceImpl  implements DeliveryFlowWriteService {
	@Autowired
	private DeliveryFlowWriteManage deliveryFlowWriteManage;

	@Override
	public Long insertDeliveryFlowWithTx(DeliveryFlowDTO dto) {
		DeliveryFlowPO po = DeliveryFlowConverter.toPO(dto);
		Long rt = deliveryFlowWriteManage.insertDeliveryFlowWithTx(po);		
		return rt;
	}

	@Override
	public int updateDeliveryFlowWithTx(DeliveryFlowDTO dto) {
		DeliveryFlowPO po = DeliveryFlowConverter.toPO(dto);
		int rt = deliveryFlowWriteManage.updateDeliveryFlowWithTx(po);		
		return rt;
	}

	@Override
	public int deleteDeliveryFlowWithTx(DeliveryFlowDTO dto) {
		DeliveryFlowPO po = DeliveryFlowConverter.toPO(dto);
		int rt = deliveryFlowWriteManage.deleteDeliveryFlowWithTx(po);		
		return rt;
	}
}
	