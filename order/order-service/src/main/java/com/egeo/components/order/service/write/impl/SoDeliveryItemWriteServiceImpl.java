package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoDeliveryItemWriteService;
import com.egeo.components.order.manage.write.SoDeliveryItemWriteManage;
import com.egeo.components.order.converter.SoDeliveryItemConverter;
import com.egeo.components.order.dto.SoDeliveryItemDTO;
import com.egeo.components.order.po.SoDeliveryItemPO;

@Service("soDeliveryItemWriteService")
public class SoDeliveryItemWriteServiceImpl  implements SoDeliveryItemWriteService {
	@Autowired
	private SoDeliveryItemWriteManage soDeliveryItemWriteManage;

	@Override
	public int insertSoDeliveryItemWithTx(SoDeliveryItemDTO dto) {
		SoDeliveryItemPO po = SoDeliveryItemConverter.toPO(dto);
		int rt = soDeliveryItemWriteManage.insertSoDeliveryItemWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoDeliveryItemWithTx(SoDeliveryItemDTO dto) {
		SoDeliveryItemPO po = SoDeliveryItemConverter.toPO(dto);
		int rt = soDeliveryItemWriteManage.updateSoDeliveryItemWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoDeliveryItemWithTx(SoDeliveryItemDTO dto) {
		SoDeliveryItemPO po = SoDeliveryItemConverter.toPO(dto);
		int rt = soDeliveryItemWriteManage.deleteSoDeliveryItemWithTx(po);		
		return rt;
	}
}
	