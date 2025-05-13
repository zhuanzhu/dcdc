package com.egeo.components.pay.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.dao.write.PayWeixinLogWriteDAO;
import com.egeo.components.pay.manage.write.PayWeixinLogWriteManage;
import com.egeo.components.pay.po.PayWeixinLogPO;
import com.egeo.exception.BusinessException;

@Service
public class PayWeixinLogWriteManageImpl implements PayWeixinLogWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PayWeixinLogWriteDAO payWeixinLogWriteDAO;

	@Override
	public Long insertPayWeixinLogWithTx(PayWeixinLogPO po) {
		
		int i ;
		try {
				i = payWeixinLogWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updatePayWeixinLogWithTx(PayWeixinLogPO po) {
		int i;
		i = payWeixinLogWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deletePayWeixinLogWithTx(PayWeixinLogPO po) {
		int i;
		i = payWeixinLogWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	