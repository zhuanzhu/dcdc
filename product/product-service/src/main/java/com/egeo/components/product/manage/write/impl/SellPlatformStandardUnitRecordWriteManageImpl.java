package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.SellPlatformStandardUnitRecordWriteManage;
import com.egeo.components.product.dao.write.SellPlatformStandardUnitRecordWriteDAO;
import com.egeo.components.product.po.SellPlatformStandardUnitRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class SellPlatformStandardUnitRecordWriteManageImpl implements SellPlatformStandardUnitRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SellPlatformStandardUnitRecordWriteDAO sellPlatformStandardUnitRecordWriteDAO;

	@Override
	public Long insertSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordPO po) {
		
		int i ;
		try {
				i = sellPlatformStandardUnitRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordPO po) {
		int i;
		i = sellPlatformStandardUnitRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSellPlatformStandardUnitRecordWithTx(SellPlatformStandardUnitRecordPO po) {
		int i;
		i = sellPlatformStandardUnitRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	