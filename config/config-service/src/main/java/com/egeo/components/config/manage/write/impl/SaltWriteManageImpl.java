package com.egeo.components.config.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.write.SaltWriteDAO;
import com.egeo.components.config.manage.write.SaltWriteManage;
import com.egeo.components.config.po.SaltPO;
import com.egeo.exception.BusinessException;

@Service
public class SaltWriteManageImpl implements SaltWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SaltWriteDAO saltWriteDAO;

	@Override
	public Long insertSaltWithTx(SaltPO po) {
		
		int i ;
		try {
				i = saltWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSaltWithTx(SaltPO po) {
		int i;
		i = saltWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSaltWithTx(SaltPO po) {
		int i;
		i = saltWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	