package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.TrialApplyDTO;


public interface TrialApplyWriteService {

	public Long insertTrialApplyWithTx(TrialApplyDTO dto);

	public int updateTrialApplyWithTx(TrialApplyDTO dto);

	public int deleteTrialApplyWithTx(TrialApplyDTO dto);
}
	