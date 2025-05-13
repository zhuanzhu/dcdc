package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsCfgValueWriteManage;
import com.egeo.components.cms.dao.write.CmsCfgValueWriteDAO;
import com.egeo.components.cms.po.CmsCfgValuePO;
import com.egeo.exception.BusinessException;

@Service
public class CmsCfgValueWriteManageImpl implements CmsCfgValueWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsCfgValueWriteDAO cmsCfgValueWriteDAO;

	@Override
	public Long insertCmsCfgValueWithTx(CmsCfgValuePO po) {
		
		int i ;
		try {
				i = cmsCfgValueWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsCfgValueWithTx(CmsCfgValuePO po) {
		int i;
		i = cmsCfgValueWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsCfgValueWithTx(CmsCfgValuePO po) {
		int i;
		i = cmsCfgValueWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	