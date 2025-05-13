package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoPackageBoxWriteService;
import com.egeo.components.order.manage.write.SoPackageBoxWriteManage;
import com.egeo.components.order.converter.SoPackageBoxConverter;
import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.components.order.po.SoPackageBoxPO;

@Service("soPackageBoxWriteService")
public class SoPackageBoxWriteServiceImpl  implements SoPackageBoxWriteService {
	@Autowired
	private SoPackageBoxWriteManage soPackageBoxWriteManage;

	@Override
	public Long insertSoPackageBoxWithTx(SoPackageBoxDTO dto) {
		SoPackageBoxPO po = SoPackageBoxConverter.toPO(dto);
		Long rt = soPackageBoxWriteManage.insertSoPackageBoxWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoPackageBoxWithTx(SoPackageBoxDTO dto) {
		SoPackageBoxPO po = SoPackageBoxConverter.toPO(dto);
		int rt = soPackageBoxWriteManage.updateSoPackageBoxWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoPackageBoxWithTx(SoPackageBoxDTO dto) {
		SoPackageBoxPO po = SoPackageBoxConverter.toPO(dto);
		int rt = soPackageBoxWriteManage.deleteSoPackageBoxWithTx(po);		
		return rt;
	}
}
	