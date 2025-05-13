package com.egeo.components.finance.service.write;

import com.egeo.components.finance.dto.ReasonCompanyDTO;


public interface ReasonCompanyWriteService {

	public Long insertReasonCompanyWithTx(ReasonCompanyDTO dto);

	public int updateReasonCompanyWithTx(ReasonCompanyDTO dto);

	public int deleteReasonCompanyWithTx(ReasonCompanyDTO dto);
}
	