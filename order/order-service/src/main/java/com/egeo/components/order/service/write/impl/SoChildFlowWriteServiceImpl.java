package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoChildFlowWriteService;
import com.egeo.components.order.manage.write.SoChildFlowWriteManage;
import com.egeo.components.order.converter.SoChildFlowConverter;
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.components.order.po.SoChildFlowPO;

@Service("soChildFlowWriteService")
public class SoChildFlowWriteServiceImpl  implements SoChildFlowWriteService {
	@Autowired
	private SoChildFlowWriteManage soChildFlowWriteManage;

	@Override
	public Long insertSoChildFlowWithTx(SoChildFlowDTO dto) {
		SoChildFlowPO po = SoChildFlowConverter.toPO(dto);
		Long rt = soChildFlowWriteManage.insertSoChildFlowWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoChildFlowWithTx(SoChildFlowDTO dto) {
		SoChildFlowPO po = SoChildFlowConverter.toPO(dto);
		int rt = soChildFlowWriteManage.updateSoChildFlowWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoChildFlowWithTx(SoChildFlowDTO dto) {
		SoChildFlowPO po = SoChildFlowConverter.toPO(dto);
		int rt = soChildFlowWriteManage.deleteSoChildFlowWithTx(po);		
		return rt;
	}
}
	