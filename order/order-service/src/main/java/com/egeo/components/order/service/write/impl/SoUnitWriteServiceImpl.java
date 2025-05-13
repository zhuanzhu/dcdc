package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoUnitWriteService;
import com.egeo.components.order.manage.write.SoUnitWriteManage;
import com.egeo.components.order.converter.SoUnitConverter;
import com.egeo.components.order.dto.SoUnitDTO;
import com.egeo.components.order.po.SoUnitPO;

@Service("soUnitWriteService")
public class SoUnitWriteServiceImpl  implements SoUnitWriteService {
	@Autowired
	private SoUnitWriteManage soUnitWriteManage;

	@Override
	public Long insertSoUnitWithTx(SoUnitDTO dto) {
		SoUnitPO po = SoUnitConverter.toPO(dto);
		Long rt = soUnitWriteManage.insertSoUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoUnitWithTx(SoUnitDTO dto) {
		SoUnitPO po = SoUnitConverter.toPO(dto);
		int rt = soUnitWriteManage.updateSoUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoUnitWithTx(SoUnitDTO dto) {
		SoUnitPO po = SoUnitConverter.toPO(dto);
		int rt = soUnitWriteManage.deleteSoUnitWithTx(po);		
		return rt;
	}
}
	