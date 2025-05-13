package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.LimitRuleUserWriteManage;
import com.egeo.components.order.dao.write.LimitRuleUserWriteDAO;
import com.egeo.components.order.po.LimitRuleUserPO;
import com.egeo.exception.BusinessException;

@Service
public class LimitRuleUserWriteManageImpl implements LimitRuleUserWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleUserWriteDAO limitRuleUserWriteDAO;

	@Override
	public Long insertLimitRuleUserWithTx(LimitRuleUserPO po) {
		
		int i ;
		try {
				i = limitRuleUserWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLimitRuleUserWithTx(LimitRuleUserPO po) {
		int i;
		i = limitRuleUserWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLimitRuleUserWithTx(LimitRuleUserPO po) {
		int i;
		i = limitRuleUserWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	