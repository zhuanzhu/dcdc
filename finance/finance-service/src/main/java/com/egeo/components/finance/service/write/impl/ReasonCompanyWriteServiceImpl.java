package com.egeo.components.finance.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.ReasonCompanyConverter;
import com.egeo.components.finance.dto.ReasonCompanyDTO;
import com.egeo.components.finance.manage.write.ReasonCompanyWriteManage;
import com.egeo.components.finance.po.ReasonCompanyPO;
import com.egeo.components.finance.service.write.ReasonCompanyWriteService;

@Service("reasonCompanyWriteService")
public class ReasonCompanyWriteServiceImpl  implements ReasonCompanyWriteService {
	@Autowired
	private ReasonCompanyWriteManage reasonCompanyWriteManage;

	@Override
	public Long insertReasonCompanyWithTx(ReasonCompanyDTO dto) {
		ReasonCompanyPO po = ReasonCompanyConverter.toPO(dto);
		Long rt = reasonCompanyWriteManage.insertReasonCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateReasonCompanyWithTx(ReasonCompanyDTO dto) {
		ReasonCompanyPO po = ReasonCompanyConverter.toPO(dto);
		int rt = reasonCompanyWriteManage.updateReasonCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteReasonCompanyWithTx(ReasonCompanyDTO dto) {
		ReasonCompanyPO po = ReasonCompanyConverter.toPO(dto);
		int rt = reasonCompanyWriteManage.deleteReasonCompanyWithTx(po);		
		return rt;
	}
}
	