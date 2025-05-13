package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StoreMenuNodeWriteManage;
import com.egeo.components.product.dao.write.StoreMenuNodeStandardUnitWriteDAO;
import com.egeo.components.product.dao.write.StoreMenuNodeWriteDAO;
import com.egeo.components.product.po.StoreMenuNodePO;
import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;
import com.egeo.exception.BusinessException;

@Service
public class StoreMenuNodeWriteManageImpl implements StoreMenuNodeWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreMenuNodeWriteDAO storeMenuNodeWriteDAO;
	
	@Autowired
	private StoreMenuNodeStandardUnitWriteDAO storeMenuNodeStandardUnitWriteDAO;

	@Override
	public Long insertStoreMenuNodeWithTx(StoreMenuNodePO po) {
		
		int i ;
		try {
				i = storeMenuNodeWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStoreMenuNodeWithTx(StoreMenuNodePO po) {
		int i;
		i = storeMenuNodeWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreMenuNodeWithTx(StoreMenuNodePO po) {
		int i;
		i = storeMenuNodeWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		
		StoreMenuNodeStandardUnitPO storeMenuNodeStandardUnitPO = new StoreMenuNodeStandardUnitPO();
		storeMenuNodeStandardUnitPO.setStoreMenuNodeId(po.getId());
		storeMenuNodeStandardUnitPO.setPlatformId(po.getPlatformId());
		storeMenuNodeStandardUnitWriteDAO.deleteByPara(storeMenuNodeStandardUnitPO);
		return i;
	}	
}
	