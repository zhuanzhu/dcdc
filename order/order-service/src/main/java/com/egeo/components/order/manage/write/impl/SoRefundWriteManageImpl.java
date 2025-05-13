package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.SoRefundWriteManage;
import com.egeo.components.order.dao.write.SoRefundWriteDAO;
import com.egeo.components.order.po.SoRefundPO;
import com.egeo.exception.BusinessException;

@Service
public class SoRefundWriteManageImpl implements SoRefundWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SoRefundWriteDAO soRefundWriteDAO;

	@Override
	public Long insertSoRefundWithTx(SoRefundPO po) {
		
		int i ;
		try {
				i = soRefundWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoRefundWithTx(SoRefundPO po) {
		int i;
		i = soRefundWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoRefundWithTx(SoRefundPO po) {
		int i;
		i = soRefundWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	