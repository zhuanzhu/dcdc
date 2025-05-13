package com.egeo.components.pay.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.write.PayAliLogWriteDAO;
import com.egeo.components.pay.manage.write.PayAliLogWriteManage;
import com.egeo.components.pay.po.PayAliLogPO;
import com.egeo.exception.BusinessException;

@Service
public class PayAliLogWriteManageImpl implements PayAliLogWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PayAliLogWriteDAO payAliLogWriteDAO;

	@Override
	public Long insertPayAliLogWithTx(PayAliLogPO po) {
		
		int i ;
		try {
				i = payAliLogWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updatePayAliLogWithTx(PayAliLogPO po) {
		int i;
		i = payAliLogWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deletePayAliLogWithTx(PayAliLogPO po) {
		int i;
		i = payAliLogWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	