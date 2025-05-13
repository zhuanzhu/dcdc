package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StoreMenuTreeWriteManage;
import com.egeo.components.product.dao.write.StoreMenuTreeWriteDAO;
import com.egeo.components.product.po.StoreMenuTreePO;
import com.egeo.exception.BusinessException;

@Service
public class StoreMenuTreeWriteManageImpl implements StoreMenuTreeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreMenuTreeWriteDAO storeMenuTreeWriteDAO;

	@Override
	public Long insertStoreMenuTreeWithTx(StoreMenuTreePO po) {
		
		int i ;
		try {
				i = storeMenuTreeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStoreMenuTreeWithTx(StoreMenuTreePO po) {
		int i;
		i = storeMenuTreeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreMenuTreeWithTx(StoreMenuTreePO po) {
		int i;
		i = storeMenuTreeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	