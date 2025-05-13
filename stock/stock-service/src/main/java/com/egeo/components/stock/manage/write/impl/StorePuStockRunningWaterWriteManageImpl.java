package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.StorePuStockRunningWaterWriteManage;
import com.egeo.components.stock.dao.write.StorePuStockRunningWaterWriteDAO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.exception.BusinessException;

@Service
public class StorePuStockRunningWaterWriteManageImpl implements StorePuStockRunningWaterWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StorePuStockRunningWaterWriteDAO storePuStockRunningWaterWriteDAO;

	@Override
	public Long insertStorePuStockRunningWaterWithTx(StorePuStockRunningWaterPO po) {
		
		int i ;
		try {
				i = storePuStockRunningWaterWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStorePuStockRunningWaterWithTx(StorePuStockRunningWaterPO po) {
		int i;
		i = storePuStockRunningWaterWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStorePuStockRunningWaterWithTx(StorePuStockRunningWaterPO po) {
		int i;
		i = storePuStockRunningWaterWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	