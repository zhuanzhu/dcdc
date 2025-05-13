package com.egeo.components.finance.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.AccountFlowConverter;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.manage.write.AccountFlowWriteManage;
import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.components.finance.service.write.AccountFlowWriteService;

@Service("accountFlowWriteService")
public class AccountFlowWriteServiceImpl  implements AccountFlowWriteService {
	@Autowired
	private AccountFlowWriteManage accountFlowWriteManage;

	@Override
	public Long insertAccountFlowWithTx(AccountFlowDTO dto) {
		AccountFlowPO po = AccountFlowConverter.toPO(dto);
		Long rt = accountFlowWriteManage.insertAccountFlowWithTx(po);		
		return rt;
	}

	@Override
	public int updateAccountFlowWithTx(AccountFlowDTO dto) {
		AccountFlowPO po = AccountFlowConverter.toPO(dto);
		int rt = accountFlowWriteManage.updateAccountFlowWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAccountFlowWithTx(AccountFlowDTO dto) {
		AccountFlowPO po = AccountFlowConverter.toPO(dto);
		int rt = accountFlowWriteManage.deleteAccountFlowWithTx(po);		
		return rt;
	}

	@Override
	public void updateAccountFlowReadStatus(List<Long> ids) {
		accountFlowWriteManage.updateAccountFlowReadStatus(ids);
	}
}
	