package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoCustomerServiceWriteManage;
import com.egeo.components.order.dao.write.SoCustomerServiceWriteDAO;
import com.egeo.components.order.po.SoCustomerServicePO;
import com.egeo.exception.BusinessException;

@Service
public class SoCustomerServiceWriteManageImpl implements SoCustomerServiceWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoCustomerServiceWriteDAO soCustomerServiceWriteDAO;

	@Override
	public Long insertSoCustomerServiceWithTx(SoCustomerServicePO po) {
		
		int i ;
		try {
				i = soCustomerServiceWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoCustomerServiceWithTx(SoCustomerServicePO po) {
		int i;
		i = soCustomerServiceWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoCustomerServiceWithTx(SoCustomerServicePO po) {
		int i;
		i = soCustomerServiceWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	