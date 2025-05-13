package com.egeo.components.finance.service.write;

import java.util.List;

import com.egeo.components.finance.dto.AccountFlowDTO;


public interface AccountFlowWriteService {

	public Long insertAccountFlowWithTx(AccountFlowDTO dto);

	public int updateAccountFlowWithTx(AccountFlowDTO dto);

	public int deleteAccountFlowWithTx(AccountFlowDTO dto);
	
	void updateAccountFlowReadStatus(List<Long> ids);
	
}
	