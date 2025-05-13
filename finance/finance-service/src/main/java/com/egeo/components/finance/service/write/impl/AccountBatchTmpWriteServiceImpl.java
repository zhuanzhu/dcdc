package com.egeo.components.finance.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.AccountBatchTmpConverter;
import com.egeo.components.finance.dto.AccountBatchTmpDTO;
import com.egeo.components.finance.manage.write.AccountBatchTmpWriteManage;
import com.egeo.components.finance.po.AccountBatchTmpPO;
import com.egeo.components.finance.service.write.AccountBatchTmpWriteService;

@Service("accountBatchTmpWriteService")
public class AccountBatchTmpWriteServiceImpl implements AccountBatchTmpWriteService {
	@Autowired
	private AccountBatchTmpWriteManage accountBatchTmpWriteManage;

	@Override
	public Long insertAccountBatchTmpWithTx(AccountBatchTmpDTO dto) {
		AccountBatchTmpPO po = AccountBatchTmpConverter.toPO(dto);
		Long rt = accountBatchTmpWriteManage.insertAccountBatchTmpWithTx(po);		
		return rt;
	}

	@Override
	public int updateAccountBatchTmpWithTx(AccountBatchTmpDTO dto) {
		AccountBatchTmpPO po = AccountBatchTmpConverter.toPO(dto);
		int rt = accountBatchTmpWriteManage.updateAccountBatchTmpWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAccountBatchTmpWithTx(AccountBatchTmpDTO dto) {
		AccountBatchTmpPO po = AccountBatchTmpConverter.toPO(dto);
		int rt = accountBatchTmpWriteManage.deleteAccountBatchTmpWithTx(po);		
		return rt;
	}

	@Override
	public int changeAccountBatchTmpStatus(Long id, int status, String reason) {
		
		return accountBatchTmpWriteManage.changeAccountBatchTmpStatus(id,status,reason);
	}
}
	