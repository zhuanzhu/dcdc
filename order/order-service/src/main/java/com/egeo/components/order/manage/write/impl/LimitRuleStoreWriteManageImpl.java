package com.egeo.components.order.manage.write.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.egeo.components.order.manage.write.LimitRuleStoreWriteManage;
import com.egeo.components.order.dao.write.LimitRuleStoreWriteDAO;
import com.egeo.components.order.po.LimitRuleStorePO;
import com.egeo.exception.BusinessException;

@Service
public class LimitRuleStoreWriteManageImpl implements LimitRuleStoreWriteManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private LimitRuleStoreWriteDAO limitRuleStoreWriteDAO;

	@Override
	public Long insertLimitRuleStoreWithTx(LimitRuleStorePO po) {
		
		int i ;
		try {
				i = limitRuleStoreWriteDAO.insert(po);
				if (i == 0)
					throw new BusinessException("未能成功插入数据!");
			} catch (DuplicateKeyException e) {
				logger.error("", e);
				throw new BusinessException("路径必须唯一!");
			}
		return po.getId();
	}

	@Override
	public int updateLimitRuleStoreWithTx(LimitRuleStorePO po) {
		int i;
		i = limitRuleStoreWriteDAO.update(po);
		if (i == 0)
			throw new BusinessException("未能成功更新数据!");
		return i;
	}

	@Override
	public int deleteLimitRuleStoreWithTx(LimitRuleStorePO po) {
		int i;
		i = limitRuleStoreWriteDAO.delete(po);
		if (i == 0)
			throw new BusinessException("未能成功删除数据!");
		return i;
	}	
}
	