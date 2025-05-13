package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoDeviceWriteService;
import com.egeo.components.order.manage.write.SoDeviceWriteManage;
import com.egeo.components.order.converter.SoDeviceConverter;
import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.components.order.po.SoDevicePO;

@Service("soDeviceWriteService")
public class SoDeviceWriteServiceImpl  implements SoDeviceWriteService {
	@Autowired
	private SoDeviceWriteManage soDeviceWriteManage;

	@Override
	public Long insertSoDeviceWithTx(SoDeviceDTO dto) {
		SoDevicePO po = SoDeviceConverter.toPO(dto);
		Long rt = soDeviceWriteManage.insertSoDeviceWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoDeviceWithTx(SoDeviceDTO dto) {
		SoDevicePO po = SoDeviceConverter.toPO(dto);
		int rt = soDeviceWriteManage.updateSoDeviceWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoDeviceWithTx(SoDeviceDTO dto) {
		SoDevicePO po = SoDeviceConverter.toPO(dto);
		int rt = soDeviceWriteManage.deleteSoDeviceWithTx(po);		
		return rt;
	}
}
	