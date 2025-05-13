package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.PolymallUserWriteDAO;
import com.egeo.components.config.manage.write.PolymallUserWriteManage;
import com.egeo.components.config.po.PolymallUserPO;
import com.egeo.exception.BusinessException;

@Service
public class PolymallUserWriteManageImpl implements PolymallUserWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PolymallUserWriteDAO polymallUserWriteDAO;

	@Override
	public Long insertPolymallUserWithTx(PolymallUserPO po) {
		
		int i ;
		try {
				i = polymallUserWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updatePolymallUserWithTx(PolymallUserPO po) {
		int i;
		i = polymallUserWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deletePolymallUserWithTx(PolymallUserPO po) {
		int i;
		i = polymallUserWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	