package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoDeliveryItemWriteManage;
import com.egeo.components.order.dao.write.SoDeliveryItemWriteDAO;
import com.egeo.components.order.po.SoDeliveryItemPO;
import com.egeo.exception.BusinessException;

@Service
public class SoDeliveryItemWriteManageImpl implements SoDeliveryItemWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoDeliveryItemWriteDAO soDeliveryItemWriteDAO;

	@Override
	public int insertSoDeliveryItemWithTx(SoDeliveryItemPO po) {
		
		int i ;
		try {
				i = soDeliveryItemWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return i;
	}

	@Override
	public int updateSoDeliveryItemWithTx(SoDeliveryItemPO po) {
		int i;
		i = soDeliveryItemWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoDeliveryItemWithTx(SoDeliveryItemPO po) {
		int i;
		i = soDeliveryItemWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	