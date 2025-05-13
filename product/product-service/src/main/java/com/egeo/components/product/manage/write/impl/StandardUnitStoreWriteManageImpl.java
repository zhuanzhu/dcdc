package com.egeo.components.product.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.write.StandardUnitStoreWriteManage;
import com.egeo.components.product.dao.write.StandardUnitStoreWriteDAO;
import com.egeo.components.product.po.StandardUnitStorePO;
import com.egeo.exception.BusinessException;

import java.util.List;

@Service
public class StandardUnitStoreWriteManageImpl implements StandardUnitStoreWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StandardUnitStoreWriteDAO standardUnitStoreWriteDAO;

	@Override
	public Long insertStandardUnitStoreWithTx(StandardUnitStorePO po) {
		
		int i ;
		try {
				i = standardUnitStoreWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateStandardUnitStoreWithTx(StandardUnitStorePO po) {
		int i;
		i = standardUnitStoreWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteStandardUnitStoreWithTx(StandardUnitStorePO po) {
		int i;
		i = standardUnitStoreWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public void saveStandardUnitStore(List<StandardUnitStorePO> standardUnitStorePOList) {
		try{
		standardUnitStoreWriteDAO.saveStandardUnitStore( standardUnitStorePOList);
		}catch (Exception e){
			logger.error("saveStandardUnitStore失败,e:"+e.getMessage());
		}
	}
}
	