package com.egeo.components.order.service.write.impl;

import com.egeo.components.order.converter.SoConverter;
import com.egeo.components.order.po.SoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoThirdpartyWriteService;
import com.egeo.components.order.manage.write.SoThirdpartyWriteManage;
import com.egeo.components.order.converter.SoThirdpartyConverter;
import com.egeo.components.order.dto.SoThirdpartyDTO;
import com.egeo.components.order.po.SoThirdpartyPO;

@Service("soThirdpartyWriteService")
public class SoThirdpartyWriteServiceImpl  implements SoThirdpartyWriteService {
	@Autowired
	private SoThirdpartyWriteManage soThirdpartyWriteManage;

	@Override
	public Long insertSoThirdpartyWithTx(SoThirdpartyDTO dto) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(dto);
		Long rt = soThirdpartyWriteManage.insertSoThirdpartyWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoThirdpartyWithTx(SoThirdpartyDTO dto) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(dto);
		int rt = soThirdpartyWriteManage.updateSoThirdpartyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoThirdpartyWithTx(SoThirdpartyDTO dto) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(dto);
		int rt = soThirdpartyWriteManage.deleteSoThirdpartyWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoThirdpartyAndSoWithTx(SoThirdpartyDTO dto) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(dto);
		int rt = soThirdpartyWriteManage.updateSoThirdpartyAndSoWithTx(po);
		return rt;
	}

	@Override
	public void updateSoThirdpartyByCodeWithTx(SoThirdpartyDTO soThirdpartyDTO) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(soThirdpartyDTO);
		soThirdpartyWriteManage.updateSoThirdpartyByCodeWithTx(po);
	}

	@Override
	public void updateSoThirdpartyByThirdIdWithTx(SoThirdpartyDTO thirdpartyDTO) {
		SoThirdpartyPO po = SoThirdpartyConverter.toPO(thirdpartyDTO);
		soThirdpartyWriteManage.updateSoThirdpartyByThirdIdWithTx(po);
	}
}
	