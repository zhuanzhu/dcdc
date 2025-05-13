package com.egeo.components.stock.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.write.StoreStockChangeApplyPictureWriteManage;
import com.egeo.components.stock.dao.write.StoreStockChangeApplyPictureWriteDAO;
import com.egeo.components.stock.po.StoreStockChangeApplyPicturePO;
import com.egeo.exception.BusinessException;

@Service
public class StoreStockChangeApplyPictureWriteManageImpl implements StoreStockChangeApplyPictureWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreStockChangeApplyPictureWriteDAO storeStockChangeApplyPictureWriteDAO;

	@Override
	public Long insertStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPicturePO po) {
		
		int i ;
		try {
				i = storeStockChangeApplyPictureWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPicturePO po) {
		int i;
		i = storeStockChangeApplyPictureWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPicturePO po) {
		int i;
		i = storeStockChangeApplyPictureWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	