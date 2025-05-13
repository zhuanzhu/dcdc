package com.egeo.components.finance.manage.write;

import java.util.List;

import com.egeo.components.finance.po.AccountFlowPO;


public interface AccountFlowWriteManage {

	Long insertAccountFlowWithTx(AccountFlowPO po);

	int updateAccountFlowWithTx(AccountFlowPO po);

	int deleteAccountFlowWithTx(AccountFlowPO po);
	
	void updateAccountFlowReadStatus(List<Long> ids);
}
	