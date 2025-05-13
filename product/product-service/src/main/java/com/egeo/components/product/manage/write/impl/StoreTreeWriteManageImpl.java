package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StoreTreeWriteManage;
import com.egeo.components.product.dao.write.StoreTreeWriteDAO;
import com.egeo.components.product.po.StoreTreePO;
import com.egeo.exception.BusinessException;

@Service
public class StoreTreeWriteManageImpl implements StoreTreeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreTreeWriteDAO storeTreeWriteDAO;

	@Override
	public Long insertStoreTreeWithTx(StoreTreePO po) {
		
		int i ;
		try {
				i = storeTreeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStoreTreeWithTx(StoreTreePO po) {
		int i;
		i = storeTreeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreTreeWithTx(StoreTreePO po) {
		int i;
		i = storeTreeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	