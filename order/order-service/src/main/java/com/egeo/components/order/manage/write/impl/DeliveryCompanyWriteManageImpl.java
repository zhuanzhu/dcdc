package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.DeliveryCompanyWriteManage;
import com.egeo.components.order.dao.write.DeliveryCompanyWriteDAO;
import com.egeo.components.order.po.DeliveryCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class DeliveryCompanyWriteManageImpl implements DeliveryCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeliveryCompanyWriteDAO deliveryCompanyWriteDAO;

	@Override
	public int insertDeliveryCompanyWithTx(DeliveryCompanyPO po) {
		
		int i ;
		try {
				i = deliveryCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return i;
	}

	@Override
	public int updateDeliveryCompanyWithTx(DeliveryCompanyPO po) {
		int i;
		i = deliveryCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteDeliveryCompanyWithTx(DeliveryCompanyPO po) {
		int i;
		i = deliveryCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	