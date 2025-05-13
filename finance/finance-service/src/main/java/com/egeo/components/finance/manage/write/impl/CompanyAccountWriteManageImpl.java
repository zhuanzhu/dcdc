package com.egeo.components.finance.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.dao.write.CompanyAccountWriteDAO;
import com.egeo.components.finance.manage.write.CompanyAccountWriteManage;
import com.egeo.components.finance.po.CompanyAccountPO;

@Service
public class CompanyAccountWriteManageImpl implements CompanyAccountWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyAccountWriteDAO companyAccountWriteDAO;

	@Override
	public Long insertCompanyAccountWithTx(CompanyAccountPO po) {
		
		int i ;
		try {
				i = companyAccountWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCompanyAccountWithTx(CompanyAccountPO po) {
		int i;
		i = companyAccountWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCompanyAccountWithTx(CompanyAccountPO po) {
		int i;
		i = companyAccountWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateAccountDisable(Long id, Integer disabled) {
		return companyAccountWriteDAO.updateAccountDisable(id,disabled);
	}	
}
	