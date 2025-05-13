package com.egeo.components.product.manage.write.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StoreProductUnitWriteManage;
import com.egeo.components.product.dao.write.StoreProductUnitWriteDAO;
import com.egeo.components.product.po.StoreProductUnitPO;
import com.egeo.exception.BusinessException;

@Service
public class StoreProductUnitWriteManageImpl implements StoreProductUnitWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreProductUnitWriteDAO storeProductUnitWriteDAO;

	@Override
	public Long insertStoreProductUnitWithTx(StoreProductUnitPO po) {
		
		int i ;
		try {
				i = storeProductUnitWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStoreProductUnitWithTx(StoreProductUnitPO po) {
		int i;
		i = storeProductUnitWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStoreProductUnitWithTx(StoreProductUnitPO po) {
		int i;
		i = storeProductUnitWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int insertAllWithTx(List<StoreProductUnitPO> list) {
		return storeProductUnitWriteDAO.insertAllWithTx(list);
	}	
}
	