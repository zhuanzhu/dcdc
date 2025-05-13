package com.egeo.components.order.manage.write.impl;

import com.egeo.components.order.dao.write.SoRefundItemWriteDAO;
import com.egeo.components.order.manage.write.SoRefundItemWriteManage;
import com.egeo.components.order.po.SoRefundItemPO;
import com.egeo.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SoRefundItemWriteManageImpl implements SoRefundItemWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private SoRefundItemWriteDAO soRefundItemWriteDAO;

	@Override
	public Long insertSoRefundItemWithTx(SoRefundItemPO po) {
		try {
				int i = soRefundItemWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSoRefundItemWithTx(SoRefundItemPO po) {
		int i = soRefundItemWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSoRefundItemWithTx(SoRefundItemPO po) {
		int i = soRefundItemWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	