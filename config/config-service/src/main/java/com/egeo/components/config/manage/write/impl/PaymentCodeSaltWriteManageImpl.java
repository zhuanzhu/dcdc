package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.PaymentCodeSaltWriteDAO;
import com.egeo.components.config.manage.write.PaymentCodeSaltWriteManage;
import com.egeo.components.config.po.PaymentCodeSaltPO;
import com.egeo.exception.BusinessException;

@Service
public class PaymentCodeSaltWriteManageImpl implements PaymentCodeSaltWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PaymentCodeSaltWriteDAO paymentCodeSaltWriteDAO;

	@Override
	public Long insertPaymentCodeSaltWithTx(PaymentCodeSaltPO po) {
		
		int i ;
		try {
				i = paymentCodeSaltWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updatePaymentCodeSaltWithTx(PaymentCodeSaltPO po) {
		int i;
		i = paymentCodeSaltWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deletePaymentCodeSaltWithTx(PaymentCodeSaltPO po) {
		int i;
		i = paymentCodeSaltWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	