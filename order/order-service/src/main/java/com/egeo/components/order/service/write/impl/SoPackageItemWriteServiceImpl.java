package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoPackageItemWriteService;
import com.egeo.components.order.manage.write.SoPackageItemWriteManage;
import com.egeo.components.order.converter.SoPackageItemConverter;
import com.egeo.components.order.dto.SoPackageItemDTO;
import com.egeo.components.order.po.SoPackageItemPO;

@Service("soPackageItemWriteService")
public class SoPackageItemWriteServiceImpl  implements SoPackageItemWriteService {
	@Autowired
	private SoPackageItemWriteManage soPackageItemWriteManage;

	@Override
	public int insertSoPackageItemWithTx(SoPackageItemDTO dto) {
		SoPackageItemPO po = SoPackageItemConverter.toPO(dto);
		int rt = soPackageItemWriteManage.insertSoPackageItemWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoPackageItemWithTx(SoPackageItemDTO dto) {
		SoPackageItemPO po = SoPackageItemConverter.toPO(dto);
		int rt = soPackageItemWriteManage.updateSoPackageItemWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoPackageItemWithTx(SoPackageItemDTO dto) {
		SoPackageItemPO po = SoPackageItemConverter.toPO(dto);
		int rt = soPackageItemWriteManage.deleteSoPackageItemWithTx(po);		
		return rt;
	}
}
	