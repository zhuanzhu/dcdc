package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.CompanyUserDisabledWriteManage;
import com.egeo.components.user.dao.write.CompanyUserDisabledWriteDAO;
import com.egeo.components.user.po.CompanyUserDisabledPO;
import com.egeo.exception.BusinessException;

@Service
public class CompanyUserDisabledWriteManageImpl implements CompanyUserDisabledWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyUserDisabledWriteDAO companyUserDisabledWriteDAO;

	@Override
	public Long insertCompanyUserDisabledWithTx(CompanyUserDisabledPO po) {

		int i;
		try {
			i = companyUserDisabledWriteDAO.insert(po);
			if (i == 0)
				throw new BusinessException("未能成功插入数据!");
		} catch (DuplicateKeyException e) {
			logger.error("", e);
			throw new BusinessException("路径必须唯一!");
		}
		return po.getId();
	}

	@Override
	public int updateCompanyUserDisabledWithTx(CompanyUserDisabledPO po) {
		int i;
		i = companyUserDisabledWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCompanyUserDisabledWithTx(CompanyUserDisabledPO po) {
		int i;
		i = companyUserDisabledWriteDAO.deleteByPara(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int updateRevalidationByCompanyId(Integer revalidation, Long companyId) {

		int i = companyUserDisabledWriteDAO.updateRevalidationByCompanyId(revalidation, companyId);
		return i;
	}
}
	