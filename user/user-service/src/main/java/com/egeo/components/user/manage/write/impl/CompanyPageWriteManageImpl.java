package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.CompanyPageWriteManage;
import com.egeo.components.user.dao.write.CompanyPageWriteDAO;
import com.egeo.components.user.po.CompanyPagePO;
import com.egeo.exception.BusinessException;

@Service
public class CompanyPageWriteManageImpl implements CompanyPageWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyPageWriteDAO companyPageWriteDAO;

	@Override
	public Long insertCompanyPageWithTx(CompanyPagePO po) {
		
		int i ;
		try {
				i = companyPageWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCompanyPageWithTx(CompanyPagePO po) {
		int i;
		i = companyPageWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCompanyPageWithTx(CompanyPagePO po) {
		int i;
		i = companyPageWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	