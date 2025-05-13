package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoDeliveryWriteManage;
import com.egeo.components.order.dao.write.SoDeliveryWriteDAO;
import com.egeo.components.order.po.SoDeliveryPO;
import com.egeo.exception.BusinessException;

@Service
public class SoDeliveryWriteManageImpl implements SoDeliveryWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoDeliveryWriteDAO soDeliveryWriteDAO;

	@Override
	public int insertSoDeliveryWithTx(SoDeliveryPO po) {
		
		int i ;
		try {
				i = soDeliveryWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return i;
	}

	@Override
	public int updateSoDeliveryWithTx(SoDeliveryPO po) {
		int i;
		i = soDeliveryWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoDeliveryWithTx(SoDeliveryPO po) {
		int i;
		i = soDeliveryWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	