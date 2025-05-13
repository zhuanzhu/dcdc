package com.egeo.components.promotion.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.dao.write.FuCoinWriteDAO;
import com.egeo.components.promotion.manage.write.FuCoinWriteManage;
import com.egeo.components.promotion.po.FuCoinPO;
import com.egeo.exception.BusinessException;

@Service
public class FuCoinWriteManageImpl implements FuCoinWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FuCoinWriteDAO fuCoinWriteDAO;

	@Override
	public Long insertFuCoinWithTx(FuCoinPO po) {
		
		int i ;
		try {
				i = fuCoinWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateFuCoinWithTx(FuCoinPO po) {
		int i;
		i = fuCoinWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteFuCoinWithTx(FuCoinPO po) {
		int i;
		i = fuCoinWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	