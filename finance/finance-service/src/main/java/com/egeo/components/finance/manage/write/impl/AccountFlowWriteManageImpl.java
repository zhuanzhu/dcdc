package com.egeo.components.finance.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.dao.write.AccountFlowWriteDAO;
import com.egeo.components.finance.manage.write.AccountFlowWriteManage;
import com.egeo.components.finance.po.AccountFlowPO;

@Service
public class AccountFlowWriteManageImpl implements AccountFlowWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AccountFlowWriteDAO accountFlowWriteDAO;

	@Override
	public Long insertAccountFlowWithTx(AccountFlowPO po) {
		
		int i ;
		try {
				i = accountFlowWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateAccountFlowWithTx(AccountFlowPO po) {
		int i;
		i = accountFlowWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteAccountFlowWithTx(AccountFlowPO po) {
		int i;
		i = accountFlowWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void updateAccountFlowReadStatus(List<Long> ids) {
		accountFlowWriteDAO.updateAccountFlowReadStatus(ids);
	}	
}
	