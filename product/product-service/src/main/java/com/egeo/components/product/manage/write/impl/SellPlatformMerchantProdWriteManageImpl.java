package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SellPlatformMerchantProdWriteManage;
import com.egeo.components.product.dao.write.SellPlatformMerchantProdWriteDAO;
import com.egeo.components.product.po.SellPlatformMerchantProdPO;
import com.egeo.exception.BusinessException;

@Service
public class SellPlatformMerchantProdWriteManageImpl implements SellPlatformMerchantProdWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellPlatformMerchantProdWriteDAO sellPlatformMerchantProdWriteDAO;

	@Override
	public Long insertSellPlatformMerchantProdWithTx(SellPlatformMerchantProdPO po) {
		
		int i ;
		try {
				i = sellPlatformMerchantProdWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSellPlatformMerchantProdWithTx(SellPlatformMerchantProdPO po) {
		int i;
		i = sellPlatformMerchantProdWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSellPlatformMerchantProdWithTx(SellPlatformMerchantProdPO po) {
		int i;
		i = sellPlatformMerchantProdWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	