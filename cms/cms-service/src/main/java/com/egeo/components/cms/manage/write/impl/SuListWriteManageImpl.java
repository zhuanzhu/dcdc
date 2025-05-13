package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.SuListWriteManage;
import com.egeo.components.cms.dao.write.SuListWriteDAO;
import com.egeo.components.cms.po.SuListPO;
import com.egeo.exception.BusinessException;

@Service
public class SuListWriteManageImpl implements SuListWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SuListWriteDAO suListWriteDAO;

	@Override
	public Long insertSuListWithTx(SuListPO po) {
		
		int i ;
		try {
				i = suListWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateSuListWithTx(SuListPO po) {
		int i;
		i = suListWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteSuListWithTx(SuListPO po) {
		int i;
		i = suListWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	