package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoCustomerServiceWriteService;
import com.egeo.components.order.manage.write.SoCustomerServiceWriteManage;
import com.egeo.components.order.converter.SoCustomerServiceConverter;
import com.egeo.components.order.dto.SoCustomerServiceDTO;
import com.egeo.components.order.po.SoCustomerServicePO;

@Service("soCustomerServiceWriteService")
public class SoCustomerServiceWriteServiceImpl  implements SoCustomerServiceWriteService {
	@Autowired
	private SoCustomerServiceWriteManage soCustomerServiceWriteManage;

	@Override
	public Long insertSoCustomerServiceWithTx(SoCustomerServiceDTO dto) {
		SoCustomerServicePO po = SoCustomerServiceConverter.toPO(dto);
		Long rt = soCustomerServiceWriteManage.insertSoCustomerServiceWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoCustomerServiceWithTx(SoCustomerServiceDTO dto) {
		SoCustomerServicePO po = SoCustomerServiceConverter.toPO(dto);
		int rt = soCustomerServiceWriteManage.updateSoCustomerServiceWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoCustomerServiceWithTx(SoCustomerServiceDTO dto) {
		SoCustomerServicePO po = SoCustomerServiceConverter.toPO(dto);
		int rt = soCustomerServiceWriteManage.deleteSoCustomerServiceWithTx(po);		
		return rt;
	}
}
	