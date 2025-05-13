package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsCfgKeyWriteManage;
import com.egeo.components.cms.dao.write.CmsCfgKeyWriteDAO;
import com.egeo.components.cms.po.CmsCfgKeyPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsCfgKeyWriteManageImpl implements CmsCfgKeyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsCfgKeyWriteDAO cmsCfgKeyWriteDAO;

	@Override
	public Long insertCmsCfgKeyWithTx(CmsCfgKeyPO po) {
		
		int i ;
		try {
				i = cmsCfgKeyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsCfgKeyWithTx(CmsCfgKeyPO po) {
		int i;
		i = cmsCfgKeyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsCfgKeyWithTx(CmsCfgKeyPO po) {
		int i;
		i = cmsCfgKeyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	