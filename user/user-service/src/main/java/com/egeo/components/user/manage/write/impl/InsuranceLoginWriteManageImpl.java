package com.egeo.components.user.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.write.InsuranceLoginWriteManage;
import com.egeo.components.user.dao.write.InsuranceLoginWriteDAO;
import com.egeo.components.user.po.InsuranceLoginPO;
import com.egeo.exception.BusinessException;

@Service
public class InsuranceLoginWriteManageImpl implements InsuranceLoginWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private InsuranceLoginWriteDAO insuranceLoginWriteDAO;

	@Override
	public Long insertInsuranceLoginWithTx(InsuranceLoginPO po) {
		
		int i ;
		try {
				i = insuranceLoginWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateInsuranceLoginWithTx(InsuranceLoginPO po) {
		int i;
		i = insuranceLoginWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteInsuranceLoginWithTx(InsuranceLoginPO po) {
		int i;
		i = insuranceLoginWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}

	@Override
	public int insuranceLoginWithTx(InsuranceLoginPO po) {
		return insuranceLoginWriteDAO.insuranceLogin(po);
	}

}
	