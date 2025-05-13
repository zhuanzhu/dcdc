package com.egeo.components.finance.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.common.BusinessException;
import com.egeo.components.finance.dao.write.ReasonCompanyWriteDAO;
import com.egeo.components.finance.manage.write.ReasonCompanyWriteManage;
import com.egeo.components.finance.po.ReasonCompanyPO;

@Service
public class ReasonCompanyWriteManageImpl implements ReasonCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ReasonCompanyWriteDAO reasonCompanyWriteDAO;

	@Override
	public Long insertReasonCompanyWithTx(ReasonCompanyPO po) {
		
		int i ;
		try {
				i = reasonCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateReasonCompanyWithTx(ReasonCompanyPO po) {
		int i;
		i = reasonCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteReasonCompanyWithTx(ReasonCompanyPO po) {
		int i;
		i = reasonCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	