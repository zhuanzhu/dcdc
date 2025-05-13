package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.DeliveryCompanyWriteService;
import com.egeo.components.order.manage.write.DeliveryCompanyWriteManage;
import com.egeo.components.order.converter.DeliveryCompanyConverter;
import com.egeo.components.order.dto.DeliveryCompanyDTO;
import com.egeo.components.order.po.DeliveryCompanyPO;

@Service("deliveryCompanyWriteService")
public class DeliveryCompanyWriteServiceImpl  implements DeliveryCompanyWriteService {
	@Autowired
	private DeliveryCompanyWriteManage deliveryCompanyWriteManage;

	@Override
	public int insertDeliveryCompanyWithTx(DeliveryCompanyDTO dto) {
		DeliveryCompanyPO po = DeliveryCompanyConverter.toPO(dto);
		int rt = deliveryCompanyWriteManage.insertDeliveryCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateDeliveryCompanyWithTx(DeliveryCompanyDTO dto) {
		DeliveryCompanyPO po = DeliveryCompanyConverter.toPO(dto);
		int rt = deliveryCompanyWriteManage.updateDeliveryCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteDeliveryCompanyWithTx(DeliveryCompanyDTO dto) {
		DeliveryCompanyPO po = DeliveryCompanyConverter.toPO(dto);
		int rt = deliveryCompanyWriteManage.deleteDeliveryCompanyWithTx(po);		
		return rt;
	}
}
	