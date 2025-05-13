package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.StockDictWriteManage;
import com.egeo.components.stock.dao.write.StockDictWriteDAO;
import com.egeo.components.stock.po.StockDictPO;
import com.egeo.exception.BusinessException;

@Service
public class StockDictWriteManageImpl implements StockDictWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StockDictWriteDAO stockDictWriteDAO;

	@Override
	public Long insertStockDictWithTx(StockDictPO po) {
		
		int i ;
		try {
				i = stockDictWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStockDictWithTx(StockDictPO po) {
		int i;
		i = stockDictWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStockDictWithTx(StockDictPO po) {
		int i;
		i = stockDictWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	