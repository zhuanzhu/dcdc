package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.TrialApplyConverter;
import com.egeo.components.user.dto.TrialApplyDTO;
import com.egeo.components.user.manage.write.TrialApplyWriteManage;
import com.egeo.components.user.po.TrialApplyPO;
import com.egeo.components.user.service.write.TrialApplyWriteService;

@Service("trialApplyWriteService")
public class TrialApplyWriteServiceImpl implements TrialApplyWriteService {
	@Autowired
	private TrialApplyWriteManage trialApplyWriteManage;

	@Override
	public Long insertTrialApplyWithTx(TrialApplyDTO dto) {
		TrialApplyPO po = TrialApplyConverter.toPO(dto);
		Long rt = trialApplyWriteManage.insertTrialApplyWithTx(po);		
		return rt;
	}

	@Override
	public int updateTrialApplyWithTx(TrialApplyDTO dto) {
		TrialApplyPO po = TrialApplyConverter.toPO(dto);
		int rt = trialApplyWriteManage.updateTrialApplyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteTrialApplyWithTx(TrialApplyDTO dto) {
		TrialApplyPO po = TrialApplyConverter.toPO(dto);
		int rt = trialApplyWriteManage.deleteTrialApplyWithTx(po);		
		return rt;
	}
}
	