package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.FuCoinHistoryWriteDAO;
import com.egeo.components.promotion.manage.write.FuCoinHistoryWriteManage;
import com.egeo.components.promotion.po.FuCoinHistoryPO;
import com.egeo.exception.BusinessException;

@Service
public class FuCoinHistoryWriteManageImpl implements FuCoinHistoryWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FuCoinHistoryWriteDAO fuCoinHistoryWriteDAO;

	@Override
	public Long insertFuCoinHistoryWithTx(FuCoinHistoryPO po) {
		
		int i ;
		try {
				i = fuCoinHistoryWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFuCoinHistoryWithTx(FuCoinHistoryPO po) {
		int i;
		i = fuCoinHistoryWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFuCoinHistoryWithTx(FuCoinHistoryPO po) {
		int i;
		i = fuCoinHistoryWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	