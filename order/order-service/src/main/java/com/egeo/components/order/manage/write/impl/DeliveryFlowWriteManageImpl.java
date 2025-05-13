package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.DeliveryFlowWriteManage;
import com.egeo.components.order.dao.write.DeliveryFlowWriteDAO;
import com.egeo.components.order.po.DeliveryFlowPO;
import com.egeo.exception.BusinessException;

@Service
public class DeliveryFlowWriteManageImpl implements DeliveryFlowWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DeliveryFlowWriteDAO deliveryFlowWriteDAO;

	@Override
	public Long insertDeliveryFlowWithTx(DeliveryFlowPO po) {
		
		int i ;
		try {
				i = deliveryFlowWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateDeliveryFlowWithTx(DeliveryFlowPO po) {
		int i;
		i = deliveryFlowWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteDeliveryFlowWithTx(DeliveryFlowPO po) {
		int i;
		i = deliveryFlowWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	