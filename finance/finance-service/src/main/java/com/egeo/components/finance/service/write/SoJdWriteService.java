package com.egeo.components.finance.service.write;

import com.egeo.components.finance.po.SoJdPO;


public interface SoJdWriteService {

	public Long insertAccountFlowWithTx(SoJdPO dto);

	public int updateAccountFlowWithTx(SoJdPO dto);

	public int deleteAccountFlowWithTx(SoJdPO dto);
	
	
}
	