package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoDeliveryWriteService;
import com.egeo.components.order.manage.write.SoDeliveryWriteManage;
import com.egeo.components.order.converter.SoDeliveryConverter;
import com.egeo.components.order.dto.SoDeliveryDTO;
import com.egeo.components.order.po.SoDeliveryPO;

@Service("soDeliveryWriteService")
public class SoDeliveryWriteServiceImpl  implements SoDeliveryWriteService {
	@Autowired
	private SoDeliveryWriteManage soDeliveryWriteManage;

	@Override
	public int insertSoDeliveryWithTx(SoDeliveryDTO dto) {
		SoDeliveryPO po = SoDeliveryConverter.toPO(dto);
		int rt = soDeliveryWriteManage.insertSoDeliveryWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoDeliveryWithTx(SoDeliveryDTO dto) {
		SoDeliveryPO po = SoDeliveryConverter.toPO(dto);
		int rt = soDeliveryWriteManage.updateSoDeliveryWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoDeliveryWithTx(SoDeliveryDTO dto) {
		SoDeliveryPO po = SoDeliveryConverter.toPO(dto);
		int rt = soDeliveryWriteManage.deleteSoDeliveryWithTx(po);		
		return rt;
	}
}
	