package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.CompanyCallCenterWriteManage;
import com.egeo.components.user.dao.write.CompanyCallCenterWriteDAO;
import com.egeo.components.user.po.CompanyCallCenterPO;
import com.egeo.exception.BusinessException;

@Service
public class CompanyCallCenterWriteManageImpl implements CompanyCallCenterWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyCallCenterWriteDAO companyCallCenterWriteDAO;

	@Override
	public Long insertCompanyCallCenterWithTx(CompanyCallCenterPO po) {
		
		int i ;
		try {
				i = companyCallCenterWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCompanyCallCenterWithTx(CompanyCallCenterPO po) {
		int i;
		i = companyCallCenterWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCompanyCallCenterWithTx(CompanyCallCenterPO po) {
		int i;
		i = companyCallCenterWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	