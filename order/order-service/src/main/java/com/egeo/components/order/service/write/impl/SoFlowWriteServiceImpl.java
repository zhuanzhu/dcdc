package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.SoFlowWriteService;
import com.egeo.components.order.manage.write.SoFlowWriteManage;
import com.egeo.components.order.converter.SoFlowConverter;
import com.egeo.components.order.dto.SoFlowDTO;
import com.egeo.components.order.po.SoFlowPO;

@Service("soFlowWriteService")
public class SoFlowWriteServiceImpl  implements SoFlowWriteService {
	@Autowired
	private SoFlowWriteManage soFlowWriteManage;

	@Override
	public Long insertSoFlowWithTx(SoFlowDTO dto) {
		SoFlowPO po = SoFlowConverter.toPO(dto);
		Long rt = soFlowWriteManage.insertSoFlowWithTx(po);		
		return rt;
	}

	@Override
	public int updateSoFlowWithTx(SoFlowDTO dto) {
		SoFlowPO po = SoFlowConverter.toPO(dto);
		int rt = soFlowWriteManage.updateSoFlowWithTx(po);		
		return rt;
	}

	@Override
	public int deleteSoFlowWithTx(SoFlowDTO dto) {
		SoFlowPO po = SoFlowConverter.toPO(dto);
		int rt = soFlowWriteManage.deleteSoFlowWithTx(po);		
		return rt;
	}
}
	