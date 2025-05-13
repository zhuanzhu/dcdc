package com.egeo.components.cms.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.manage.write.InstCompanyWriteManage;
import com.egeo.components.cms.dao.write.InstCompanyWriteDAO;
import com.egeo.components.cms.po.InstCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class InstCompanyWriteManageImpl implements InstCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InstCompanyWriteDAO instCompanyWriteDAO;

	@Override
	public Long insertInstCompanyWithTx(InstCompanyPO po) {
		
		int i ;
		try {
				i = instCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateInstCompanyWithTx(InstCompanyPO po) {
		int i;
		i = instCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteInstCompanyWithTx(InstCompanyPO po) {
		int i;
		i = instCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	