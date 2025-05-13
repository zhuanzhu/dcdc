package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.TrialApplyWriteManage;
import com.egeo.components.user.dao.write.TrialApplyWriteDAO;
import com.egeo.components.user.po.TrialApplyPO;
import com.egeo.exception.BusinessException;

@Service
public class TrialApplyWriteManageImpl implements TrialApplyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TrialApplyWriteDAO trialApplyWriteDAO;

	@Override
	public Long insertTrialApplyWithTx(TrialApplyPO po) {
		
		int i ;
		try {
				i = trialApplyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateTrialApplyWithTx(TrialApplyPO po) {
		int i;
		i = trialApplyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteTrialApplyWithTx(TrialApplyPO po) {
		int i;
		i = trialApplyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	