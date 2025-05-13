package com.egeo.components.order.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoPackageTempWriteService;
import com.egeo.components.order.manage.write.SoPackageTempWriteManage;
import com.egeo.components.order.converter.SoPackageTempConverter;
import com.egeo.components.order.dto.SoPackageTempDTO;
import com.egeo.components.order.po.SoPackageTempPO;

@Service("soPackageTempWriteService")
public class SoPackageTempWriteServiceImpl  implements SoPackageTempWriteService {
	@Autowired
	private SoPackageTempWriteManage soPackageTempWriteManage;

	@Override
	public Long insertSoPackageTempWithTx(SoPackageTempDTO dto) {
		SoPackageTempPO po = SoPackageTempConverter.toPO(dto);
		Long rt = soPackageTempWriteManage.insertSoPackageTempWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoPackageTempWithTx(SoPackageTempDTO dto) {
		SoPackageTempPO po = SoPackageTempConverter.toPO(dto);
		int rt = soPackageTempWriteManage.updateSoPackageTempWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoPackageTempWithTx(SoPackageTempDTO dto) {
		SoPackageTempPO po = SoPackageTempConverter.toPO(dto);
		int rt = soPackageTempWriteManage.deleteSoPackageTempWithTx(po);		
		return rt;
	}

	@Override
	public String insertSoPackageTempListWithTx(List<SoPackageTempDTO> dtoList) {
		
		return soPackageTempWriteManage.insertSoPackageTempListWithTx(SoPackageTempConverter.toPO(dtoList));
	}
}
	