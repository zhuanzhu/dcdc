package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.LimitRuleCompanyWriteManage;
import com.egeo.components.order.dao.write.LimitRuleCompanyWriteDAO;
import com.egeo.components.order.po.LimitRuleCompanyPO;
import com.egeo.exception.BusinessException;

@Service
public class LimitRuleCompanyWriteManageImpl implements LimitRuleCompanyWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleCompanyWriteDAO limitRuleCompanyWriteDAO;

	@Override
	public Long insertLimitRuleCompanyWithTx(LimitRuleCompanyPO po) {
		
		int i ;
		try {
				i = limitRuleCompanyWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLimitRuleCompanyWithTx(LimitRuleCompanyPO po) {
		int i;
		i = limitRuleCompanyWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLimitRuleCompanyWithTx(LimitRuleCompanyPO po) {
		int i;
		i = limitRuleCompanyWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	