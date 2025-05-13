package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.CmsDictWriteManage;
import com.egeo.components.cms.dao.write.CmsDictWriteDAO;
import com.egeo.components.cms.po.CmsDictPO;
import com.egeo.exception.BusinessException;

@Service
public class CmsDictWriteManageImpl implements CmsDictWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CmsDictWriteDAO cmsDictWriteDAO;

	@Override
	public Long insertCmsDictWithTx(CmsDictPO po) {
		
		int i ;
		try {
				i = cmsDictWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCmsDictWithTx(CmsDictPO po) {
		int i;
		i = cmsDictWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCmsDictWithTx(CmsDictPO po) {
		int i;
		i = cmsDictWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	