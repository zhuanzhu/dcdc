package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.BlessingCoinBannerWriteManage;
import com.egeo.components.product.dao.write.BlessingCoinBannerWriteDAO;
import com.egeo.components.product.po.BlessingCoinBannerPO;
import com.egeo.exception.BusinessException;

@Service
public class BlessingCoinBannerWriteManageImpl implements BlessingCoinBannerWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BlessingCoinBannerWriteDAO blessingCoinBannerWriteDAO;

	@Override
	public Long insertBlessingCoinBannerWithTx(BlessingCoinBannerPO po) {
		
		int i ;
		try {
				i = blessingCoinBannerWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateBlessingCoinBannerWithTx(BlessingCoinBannerPO po) {
		int i;
		i = blessingCoinBannerWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteBlessingCoinBannerWithTx(BlessingCoinBannerPO po) {
		int i;
		i = blessingCoinBannerWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	