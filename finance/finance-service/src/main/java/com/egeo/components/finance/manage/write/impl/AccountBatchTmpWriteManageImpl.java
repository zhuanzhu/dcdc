package com.egeo.components.finance.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.dao.write.AccountBatchTmpWriteDAO;
import com.egeo.components.finance.manage.write.AccountBatchTmpWriteManage;
import com.egeo.components.finance.po.AccountBatchTmpPO;

@Service
public class AccountBatchTmpWriteManageImpl implements AccountBatchTmpWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountBatchTmpWriteDAO accountBatchTmpWriteDAO;

	@Override
	public Long insertAccountBatchTmpWithTx(AccountBatchTmpPO po) {
		
		int i ;
		try {
				i = accountBatchTmpWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAccountBatchTmpWithTx(AccountBatchTmpPO po) {
		int i;
		i = accountBatchTmpWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAccountBatchTmpWithTx(AccountBatchTmpPO po) {
		int i;
		i = accountBatchTmpWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int changeAccountBatchTmpStatus(Long id, int status,String reason) {
		
		return accountBatchTmpWriteDAO.changeAccountBatchTmpStatus(id,status,reason);
	}

}
	