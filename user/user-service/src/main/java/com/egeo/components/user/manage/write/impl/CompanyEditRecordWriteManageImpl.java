package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.CompanyEditRecordWriteManage;
import com.egeo.components.user.dao.write.CompanyEditRecordWriteDAO;
import com.egeo.components.user.po.CompanyEditRecordPO;
import com.egeo.exception.BusinessException;

@Service
public class CompanyEditRecordWriteManageImpl implements CompanyEditRecordWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CompanyEditRecordWriteDAO companyEditRecordWriteDAO;

	@Override
	public Long insertCompanyEditRecordWithTx(CompanyEditRecordPO po) {
		
		int i ;
		try {
				i = companyEditRecordWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateCompanyEditRecordWithTx(CompanyEditRecordPO po) {
		int i;
		i = companyEditRecordWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteCompanyEditRecordWithTx(CompanyEditRecordPO po) {
		int i;
		i = companyEditRecordWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	